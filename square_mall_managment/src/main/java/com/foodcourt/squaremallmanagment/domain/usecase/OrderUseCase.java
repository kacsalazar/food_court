package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.api.IOrderServicePort;
import com.foodcourt.squaremallmanagment.domain.exception.*;
import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;
import com.foodcourt.squaremallmanagment.domain.model.order.*;
import com.foodcourt.squaremallmanagment.domain.spi.*;
import com.foodcourt.squaremallmanagment.domain.usecase.util.StatusEnum;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IUserRestPort userClientPort;
    private final ISendNotificationPort sendNotificationPort;
    private final ITraceabilityPersistencePort traceabilityPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IEmployeeRestPort employeeRestPort;

    @Override
    public void makeOrder(OrderModel orderModel) {

        Long userId = validateUserId(orderModel.getUserDni());
        validateThatDishesBelongSameRestaurant(orderModel);

        List<OrderModelReturn> orders = orderPersistencePort
                .findOrdersByIdUser(userId);

        if (!orders.isEmpty()) {
            throw new InvalidOrderException();
        }

        orderModel.setOrderDate(new Date());
        orderModel.setStatus(StatusEnum.PENDING.name());

        orderPersistencePort.makeOrder(orderModel, userId);
    }

    @Override
    public void assignOrderToEmployee(Long orderId, String employeeDni) {
        OrderUpdateModel orderModel = orderPersistencePort.findOrderById(orderId);
        Long employeeId = validateEmployeeWorkingInTheRestaurant(employeeDni, orderModel.getIdRestaurant());

        Optional.ofNullable(orderModel)
                .orElseThrow(OrderNotFoundException::new);

        if (!orderModel.getStatus().equals(StatusEnum.PENDING.name())) {
            throw new InvalidStateTransitionException();
        }
        
        orderModel.setIdChef(employeeId);
        orderModel.setStatus(StatusEnum.IN_PROGRESS.name());
        saveTraceability(orderModel, StatusEnum.PENDING.name(), StatusEnum.IN_PROGRESS.name(), orderId);
        orderPersistencePort.updateOrder(orderModel);
    }

    @Override
    public List<OrderModel> getOrdersByEmployee(String status, Integer page, Integer size, String dniEmployee) {
        return orderPersistencePort.getOrdersByEmployee(status, page, size, userClientPort.ownerExists(dniEmployee).getId());
    }

    @Override
    public void changeOrderToReady(NotificationOrderModel notificationOrderModel, Long orderId, String employeeDni) {
        OrderUpdateModel orderModel = orderPersistencePort.findOrderById(orderId);

        validateThatEmployeeIsAssignedToOrder(orderModel, employeeDni);
        checkIsAValidOrder(orderModel);

        String randomPin = sendMessageToCustomer(notificationOrderModel, orderModel.getIdClient());

        orderModel.setStatus(StatusEnum.COMPLETED.name());
        orderModel.setSecurityPin(randomPin);

        saveTraceability(orderModel, StatusEnum.IN_PROGRESS.name(), StatusEnum.COMPLETED.name(), orderId);
        orderPersistencePort.updateOrder(orderModel);
    }

    private void checkIsAValidOrder(OrderUpdateModel orderModel){
        Optional.ofNullable(orderModel)
                .orElseThrow(OrderNotFoundException::new);

        if (!orderModel.getStatus().equals(StatusEnum.IN_PROGRESS.name())) {
            throw new InvalidStateTransitionException();
        }
    }

    private String sendMessageToCustomer(NotificationOrderModel notificationOrderModel, Long customerId){
        String randomPin = generateRandomPinNumber();

        notificationOrderModel.setPhoneNumber(userClientPort.getUserById(customerId).getPhoneNumber());
        notificationOrderModel.setMessageBody(notificationOrderModel.getMessageBody().concat("Pin: ").concat(randomPin));

        sendNotificationPort.sendMessage(notificationOrderModel.getPhoneNumber(),
                notificationOrderModel.getMessageBody());

        return randomPin;
    }

    @Override
    public void deliverOrder(DeliverOrderModel deliverOrderModel, Long orderId, String employeeDni) {
        OrderUpdateModel orderModel = orderPersistencePort.findOrderById(orderId);
        validateThatEmployeeIsAssignedToOrder(orderModel, employeeDni);
        Optional.ofNullable(orderModel)
                .orElseThrow(OrderNotFoundException::new);

        if (!orderModel.getSecurityPin().equals(deliverOrderModel.getSecurityPin())) {
            throw new InvalidPinSecurityException();
        }

        if (!orderModel.getStatus().equals(StatusEnum.COMPLETED.name())) {
            throw new InvalidStateTransitionException();
        }

        orderModel.setStatus(StatusEnum.DELIVERED.name());
        saveTraceability(orderModel, StatusEnum.COMPLETED.name(), StatusEnum.DELIVERED.name(), orderId);
        orderPersistencePort.updateOrder(orderModel);
    }

    @Override
    public void cancelOrder(Long orderId, String customerDni) {
        OrderUpdateModel orderModel = orderPersistencePort.findOrderById(orderId);

        validateOrderBelongingToCustomer(orderModel, customerDni);
        Optional.ofNullable(orderModel)
                .orElseThrow(OrderNotFoundException::new);

        if (!orderModel.getStatus().equals(StatusEnum.PENDING.name()) ) {
            throw new InvalidStateTransitionException();
        }

        orderModel.setStatus(StatusEnum.CANCELLED.name());
        saveTraceability(orderModel, StatusEnum.PENDING.name(), StatusEnum.CANCELLED.name(), orderId);
        orderPersistencePort.updateOrder(orderModel);
    }

    private String generateRandomPinNumber() {
        return String.valueOf(1000 + (int)(Math.random() * 9000));
    }

    private void saveTraceability(OrderUpdateModel orderModel, String beforeState, String newState,
                                  Long orderId) {
        TraceabilityModel traceabilityModel = TraceabilityModel.builder()
                .orderId(orderId)
                .customerId(orderModel.getIdClient())
                .emailCustomer(userClientPort.getUserById(orderModel.getIdClient()).getEmail())
                .date(java.time.LocalDateTime.now())
                .beforeState(beforeState)
                .newState(newState)
                .employeeId(orderModel.getIdChef())
                .employeeEmail(userClientPort.getUserById(orderModel.getIdChef()).getEmail())
                .build();

        traceabilityPersistencePort.saveTraceability(traceabilityModel);
    }

    private Long validateUserId(String dni) {
        return Optional.ofNullable(userClientPort.ownerExists(dni))
                .orElseThrow(UserNotFoundException::new)
                .getId();
    }

    //validar que todos los platos pertenezcan al mismo restaurante
    private void validateThatDishesBelongSameRestaurant(OrderModel orderModel) {
        for (OrderModel.Dish dish : orderModel.getDishes()) {
            Long dishRestaurantId = dishPersistencePort.findDishById(dish.getDishId())
                    .getRestaurantInfo()
                    .getIdRestaurant();
            if (!dishRestaurantId.equals(orderModel.getRestaurantId())) {
                throw new DishesNotFromSameRestaurantException();
            }
        }
    }

    //validate that dishes belong to the same restaurant

    //validar que el empleado que va a entragar la orden sea el empleado asignado a la orden
    private void validateThatEmployeeIsAssignedToOrder(OrderUpdateModel order, String dni){
        Long employeeId = userClientPort.ownerExists(dni).getId();
        if (!order.getIdChef().equals(employeeId)) {
            throw new InvalidEmployeeException();
        }
    }
    //validate that the employee is assigned to the order

    //validar que la orden que quiere cancelar el usuario sea de su propiedad
    private void validateOrderBelongingToCustomer (OrderUpdateModel order, String dni){
        Long customerId = userClientPort.ownerExists(dni).getId();
        if (!order.getIdClient().equals(customerId)) {
            //cambiar empleado a cliente
            throw new InvalidEmployeeException();
        }
    }

    //validar que el empleado que se va a asignar a la orden sea un empleado del restaurante
    private Long validateEmployeeWorkingInTheRestaurant(String employeeDni, Long restaurantIdBelongingOrder) {
        Long employeeRestaurantId = employeeRestPort.getEmployeeByDni(employeeDni).getEmployeeRestaurantId();
        if (!employeeRestaurantId.equals(restaurantIdBelongingOrder)) {
            throw new InvalidEmployeeException();
        }
        return employeeRestPort.getEmployeeByDni(employeeDni).getId();
    }
}
