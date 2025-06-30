package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.api.IOrderServicePort;
import com.foodcourt.squaremallmanagment.domain.exception.*;
import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;
import com.foodcourt.squaremallmanagment.domain.model.order.*;
import com.foodcourt.squaremallmanagment.domain.spi.*;
import com.foodcourt.squaremallmanagment.domain.usecase.util.StateEnum;
import lombok.RequiredArgsConstructor;

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
    public void makeOrder(OrderModel orderModel, String userDni) {

        Long userId = getValidatedUserId(userDni);
        validateDishOwnerRestaurant(orderModel);

        List<OrderModelReturn> orders = orderPersistencePort
                .findOrdersByIdUser(userId);

        if (!orders.isEmpty()) {
            throw new InvalidOrderException();
        }

        orderModel.setUserDni(userDni);
        orderModel.setStatus(StateEnum.PENDING.name());
        orderPersistencePort.makeOrder(orderModel);
    }

    @Override
    public void assignOrderToEmployee(Long orderId, String employeeDni) {
        OrderUpdateModel orderModel = orderPersistencePort.findOrderById(orderId);

        Optional.ofNullable(orderModel)
                .orElseThrow(OrderNotFoundException::new);

        if (!orderModel.getStatus().equals(StateEnum.PENDING.name())) {
            throw new InvalidStateTransitionException();
        }

        Long employeeId = validateEmployeeRestaurant(employeeDni, orderModel.getIdRestaurant());
        orderModel.setIdChef(employeeId);
        orderModel.setStatus(StateEnum.IN_PROGRESS.name());
        saveTraceability(orderModel, StateEnum.PENDING.name(), StateEnum.IN_PROGRESS.name(), orderId);
        orderPersistencePort.updateOrder(orderModel);
    }

    @Override
    public List<OrderModel> getOrdersByEmployee(String status, Integer page, Integer size, String dniEmployee) {
        return orderPersistencePort.getOrdersByEmployee(status, page, size, userClientPort.ownerExists(dniEmployee).getId());
    }

    @Override
    public void changeOrderToReady(NotificationOrderModel notificationOrderModel, Long orderId, String employeeDni) {
        OrderUpdateModel orderModel = orderPersistencePort.findOrderById(orderId);

        validateOrderEmployee(orderModel, employeeDni);
        Optional.ofNullable(orderModel)
                .orElseThrow(OrderNotFoundException::new);

        if (!orderModel.getStatus().equals(StateEnum.IN_PROGRESS.name())) {
            throw new InvalidStateTransitionException();
        }

        String randomPin = generateRandomPinNumber();

        notificationOrderModel.setPhoneNumber(userClientPort.getUserById(orderModel.getIdClient()).getPhoneNumber());
        notificationOrderModel.setMessageBody(notificationOrderModel.getMessageBody().concat("Pin: ").concat(randomPin));

        orderModel.setStatus(StateEnum.COMPLETED.name());
        saveTraceability(orderModel, StateEnum.IN_PROGRESS.name(), StateEnum.COMPLETED.name(), orderId);
        orderModel.setSecurityPin(randomPin);

        orderPersistencePort.updateOrder(orderModel);
        sendNotificationPort.sendMessage(notificationOrderModel.getPhoneNumber(),
                notificationOrderModel.getMessageBody());
    }

    @Override
    public void deliverOrder(DeliverOrderModel deliverOrderModel, Long orderId, String employeeDni) {
        OrderUpdateModel orderModel = orderPersistencePort.findOrderById(orderId);
        validateOrderEmployee(orderModel, employeeDni);
        Optional.ofNullable(orderModel)
                .orElseThrow(OrderNotFoundException::new);

        if (!orderModel.getSecurityPin().equals(deliverOrderModel.getSecurityPin())) {
            throw new InvalidPinSecurityException();
        }

        if (!orderModel.getStatus().equals(StateEnum.COMPLETED.name())) {
            throw new InvalidStateTransitionException();
        }

        orderModel.setStatus(StateEnum.DELIVERED.name());
        saveTraceability(orderModel, StateEnum.COMPLETED.name(), StateEnum.DELIVERED.name(), orderId);
        orderPersistencePort.updateOrder(orderModel);
    }

    @Override
    public void cancelOrder(Long orderId, String customerDni) {
        OrderUpdateModel orderModel = orderPersistencePort.findOrderById(orderId);

        validateOrderCustomer(orderModel, customerDni);
        Optional.ofNullable(orderModel)
                .orElseThrow(OrderNotFoundException::new);

        if (!orderModel.getStatus().equals(StateEnum.PENDING.name()) ) {
            throw new InvalidStateTransitionException();
        }

        orderModel.setStatus(StateEnum.CANCELLED.name());
        saveTraceability(orderModel, StateEnum.PENDING.name(), StateEnum.CANCELLED.name(), orderId);
        orderPersistencePort.updateOrder(orderModel);
    }

    private String generateRandomPinNumber() {
        return String.valueOf(1000 + (int)(Math.random() * 9000));
    }

    private void saveTraceability(OrderUpdateModel orderModel, String beforeState, String newState,
                                  Long orderId) {
        TraceabilityModel traceabilityModel = TraceabilityModel.builder()
                .orderId(orderId.toString())
                .customerId(orderModel.getIdClient().toString())
                .emailCustomer(userClientPort.getUserById(orderModel.getIdClient()).getEmail())
                .date(java.time.LocalDateTime.now())
                .beforeState(beforeState)
                .newState(newState)
                .employeeId(orderModel.getIdChef().toString())
                .employeeEmail(userClientPort.getUserById(orderModel.getIdChef()).getEmail())
                .build();

        traceabilityPersistencePort.saveTraceability(traceabilityModel);
    }

    private Long getValidatedUserId(String dni) {
        return Optional.ofNullable(userClientPort.ownerExists(dni))
                .orElseThrow(UserNotFoundException::new)
                .getId();
    }

    //validar que todos los platos pertenezcan al mismo restaurante
    private void validateDishOwnerRestaurant(OrderModel orderModel) {
        for (OrderModel.Dish dish : orderModel.getDishes()) {
            Long dishRestaurantId = dishPersistencePort.findDishById(dish.getDishId())
                    .getRestaurantInfo()
                    .getIdRestaurant();
            if (!dishRestaurantId.equals(orderModel.getRestaurantId())) {
                throw new DishesNotFromSameRestaurantException();
            }
        }
    }

    //validar que el empleado que va a entragar la orden sea el empleado asignado a la orden
    private void validateOrderEmployee (OrderUpdateModel order, String dni){
        Long employeeId = userClientPort.ownerExists(dni).getId();
        if (!order.getIdChef().equals(employeeId)) {
            throw new InvalidEmployeeException();
        }
    }

    //validar que la orden que quiere cancelar el usuario sea de su propiedad
    private void validateOrderCustomer (OrderUpdateModel order, String dni){
        Long customerId = userClientPort.ownerExists(dni).getId();
        if (!order.getIdClient().equals(customerId)) {
            //cambiar empleado a cliente
            throw new InvalidEmployeeException();
        }
    }

    //validar que el empleado que se va a asignar a la orden sea un empleado del restaurante
    private Long validateEmployeeRestaurant(String employeeDni, Long restaurantIdBelongingOrder) {
        Long employeeRestaurantId = employeeRestPort.getEmployeeByDni(employeeDni).getEmployeeRestaurantId();
        if (!employeeRestaurantId.equals(restaurantIdBelongingOrder)) {
            throw new InvalidEmployeeException();
        }
        return employeeRestPort.getEmployeeByDni(employeeDni).getId();
    }
}
