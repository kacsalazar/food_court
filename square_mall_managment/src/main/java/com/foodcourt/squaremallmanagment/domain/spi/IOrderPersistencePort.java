package com.foodcourt.squaremallmanagment.domain.spi;

import com.foodcourt.squaremallmanagment.domain.model.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.OrderModelReturn;

import java.util.List;

public interface IOrderPersistencePort {
    void makeOrder(OrderModel orderModel);
    List<OrderModelReturn> findOrdersByIdUser(Long id);
}
