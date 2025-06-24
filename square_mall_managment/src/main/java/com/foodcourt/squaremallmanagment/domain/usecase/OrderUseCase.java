package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.api.IOrderServicePort;
import com.foodcourt.squaremallmanagment.domain.exception.ConstantException;
import com.foodcourt.squaremallmanagment.domain.exception.DomainException;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModelReturn;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderUpdateModel;
import com.foodcourt.squaremallmanagment.domain.spi.IOrderPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IUserClientPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IUserClientPort userClientPort;

    @Override
    public void makeOrder(OrderModel orderModel, String userDni) {
        List<OrderModelReturn> orders = orderPersistencePort
                .findOrdersByIdUser(userClientPort.ownerExists(orderModel.getUserDni()).getId());
            if (!orders.isEmpty()) {
                throw new DomainException(ConstantException.INVALID_ORDER);
            }

        orderModel.setUserDni(userDni);
        orderModel.setStatus("PENDING");
        orderPersistencePort.makeOrder(orderModel);
    }

    @Override
    public void assignOrderToEmployee(Long orderId, String employeeDni) {
        OrderUpdateModel orderModel = orderPersistencePort.findOrderById(orderId);
        if (orderModel == null) {
            //throw new DomainException(ConstantException.ORDER_NOT_FOUND);
        }
        Long employeeId = userClientPort.ownerExists(employeeDni).getId();
        orderModel.setIdChef(employeeId);
        orderModel.setStatus("IN_PROGRESS");
        orderPersistencePort.updateOrder(orderModel);
    }

    @Override
    public List<OrderModel> getOrdersByEmployee(String status, Integer page, Integer size, String dniEmployee) {
        return orderPersistencePort.getOrdersByEmployee(status, page, size, userClientPort.ownerExists(dniEmployee).getId());
    }

}
