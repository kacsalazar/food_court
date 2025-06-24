package com.foodcourt.squaremallmanagment.application.handler;

import com.foodcourt.squaremallmanagment.application.dto.request.OrderCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.response.OrderResponse;

import java.util.List;

public interface IOrderHandler {

    void makeOrder(OrderCreateRequest orderCreateRequest);
    void assignOrderToEmployee(Long orderId);
    List<OrderResponse> getOrdersByEmployee(String status, Integer page, Integer size);

}
