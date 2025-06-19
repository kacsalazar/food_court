package com.foodcourt.squaremallmanagment.application.handler;

import com.foodcourt.squaremallmanagment.application.dto.request.OrderCreateRequest;

public interface IOrderHandler {

    void makeOrder(OrderCreateRequest orderCreateRequest);

}
