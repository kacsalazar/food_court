package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.api.IOrderServicePort;
import com.foodcourt.squaremallmanagment.domain.exception.*;
import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;
import com.foodcourt.squaremallmanagment.domain.model.order.*;
import com.foodcourt.squaremallmanagment.domain.spi.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IUserClientPort userClientPort;
    private final ISendNotificationPort sendNotificationPort;
    private final ITraceabilityPersistencePort traceabilityPersistencePort;
    private final IDishPersistencePort dishPersistencePort;

    @Override
    public void makeOrder(OrderModel orderModel, String userDni) {

        Long ownerId = getValidatedUserId(userDni);

        validateDishOwnerRestaurant(orderModel);

        List<OrderModelReturn> orders = orderPersistencePort
                .findOrdersByIdUser(ownerId);

        if (!orders.isEmpty()) {
            throw new InvalidOrderException();
        }

        orderModel.setUserDni(userDni);
        orderModel.setStatus("PENDING");
        orderModel.setId(ownerId);
        orderPersistencePort.makeOrder(orderModel);
    }

    @Override
    public void assignOrderToEmployee(Long orderId, String employeeDni) {
        OrderUpdateModel orderModel = orderPersistencePort.findOrderById(orderId);

        Optional.ofNullable(orderModel)
                .orElseThrow(OrderNotFoundException::new);

        if (!orderModel.getStatus().equals("PENDING")) {
            throw new InvalidStateTransitionException();
        }

        Long employeeId = userClientPort.ownerExists(employeeDni).getId();
        orderModel.setIdChef(employeeId);
        orderModel.setStatus("IN_PROGRESS");
        saveTraceability(orderModel, "PENDING", "IN_PROGRESS", orderId);
        orderPersistencePort.updateOrder(orderModel);
    }

    @Override
    public List<OrderModel> getOrdersByEmployee(String status, Integer page, Integer size, String dniEmployee) {
        return orderPersistencePort.getOrdersByEmployee(status, page, size, userClientPort.ownerExists(dniEmployee).getId());
    }

    @Override
    public void changeOrderToReady(NotificationOrderModel notificationOrderModel, Long orderId) {
        OrderUpdateModel orderModel = orderPersistencePort.findOrderById(orderId);

        Optional.ofNullable(orderModel)
                .orElseThrow(OrderNotFoundException::new);

        if (!orderModel.getStatus().equals("IN_PROGRESS")) {
            throw new InvalidStateTransitionException();
        }

        String randomPin = generateRandomPinNumber();

        notificationOrderModel.setPhoneNumber(userClientPort.getUserById(orderModel.getIdClient()).getPhoneNumber());
        notificationOrderModel.setMessageBody(notificationOrderModel.getMessageBody().concat("Pin: ").concat(randomPin));

        orderModel.setStatus("COMPLETED");
        saveTraceability(orderModel, "IN_PROGRESS", "COMPLETED", orderId);
        orderModel.setSecurityPin(randomPin);

        orderPersistencePort.updateOrder(orderModel);
        sendNotificationPort.sendMessage(notificationOrderModel.getPhoneNumber(),
                notificationOrderModel.getMessageBody());
    }

    @Override
    public void deliverOrder(DeliverOrderModel deliverOrderModel, Long orderId) {
        OrderUpdateModel orderModel = orderPersistencePort.findOrderById(orderId);

        Optional.ofNullable(orderModel)
                .orElseThrow(OrderNotFoundException::new);

        if (!orderModel.getSecurityPin().equals(deliverOrderModel.getSecurityPin())) {
            throw new InvalidPinSecurityException();
        }

        if (!orderModel.getStatus().equals("COMPLETED")) {
            throw new InvalidStateTransitionException();
        }

        orderModel.setStatus("DELIVERED");
        saveTraceability(orderModel, "COMPLETED", "DELIVERED", orderId);
        orderPersistencePort.updateOrder(orderModel);
    }

    @Override
    public void cancelOrder(Long orderId) {
        OrderUpdateModel orderModel = orderPersistencePort.findOrderById(orderId);

        Optional.ofNullable(orderModel)
                .orElseThrow(OrderNotFoundException::new);

        if (!orderModel.getStatus().equals("PENDING") ) {
            throw new InvalidStateTransitionException();
        }

        orderModel.setStatus("CANCELED");
        saveTraceability(orderModel, "PENDING", "CANCELED", orderId);
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

}
