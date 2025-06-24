package com.foodcourt.squaremallmanagment.domain.api;

import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModelReturn;

import java.util.List;

public interface IOrderServicePort {

    void makeOrder(OrderModel orderModel, String userDni);
    void assignOrderToEmployee(Long orderId, String employeeDni);
    List<OrderModel> getOrdersByEmployee(String status, Integer page, Integer size, String dniEmployee);
}
