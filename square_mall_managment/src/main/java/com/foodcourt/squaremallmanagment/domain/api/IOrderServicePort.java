package com.foodcourt.squaremallmanagment.domain.api;

import com.foodcourt.squaremallmanagment.domain.model.OrderModel;

public interface IOrderServicePort {

    void makeOrder(OrderModel orderModel, String userDni);
}
