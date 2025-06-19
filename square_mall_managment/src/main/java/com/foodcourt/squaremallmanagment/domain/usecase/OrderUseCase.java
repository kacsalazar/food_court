package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.api.IOrderServicePort;
import com.foodcourt.squaremallmanagment.domain.exception.ConstantException;
import com.foodcourt.squaremallmanagment.domain.exception.DomainException;
import com.foodcourt.squaremallmanagment.domain.model.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.OrderModelReturn;
import com.foodcourt.squaremallmanagment.domain.spi.IOrderPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IUserClientPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IUserClientPort userClientPort;

    @Override
    public void makeOrder(OrderModel orderModel) {
        List<OrderModelReturn> orders = orderPersistencePort
                .findOrdersByIdUser(userClientPort.ownerExists(orderModel.getUserDni()).getId());
            if (!orders.isEmpty()) {
                throw new DomainException(ConstantException.INVALID_ORDER);
            }

        orderPersistencePort.makeOrder(orderModel);
    }

}
