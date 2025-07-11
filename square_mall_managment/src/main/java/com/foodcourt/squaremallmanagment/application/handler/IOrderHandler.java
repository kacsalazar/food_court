package com.foodcourt.squaremallmanagment.application.handler;

import com.foodcourt.squaremallmanagment.application.dto.request.DeliverOrderRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.NotificationRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.OrderCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.response.OrderResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.OrderUpdateResponse;

import java.util.List;

public interface IOrderHandler {

    void makeOrder(OrderCreateRequest orderCreateRequest);
    void assignOrderToEmployee(Long orderId);
    List<OrderResponse> getOrdersByEmployee(String status, Integer page, Integer size);
    void changeOrderToReady (NotificationRequest notification, Long orderId);
    void deliverOrder(DeliverOrderRequest deliverOrder, Long orderId);
    void cancelOrder(Long orderId);
    OrderUpdateResponse findOrderById(Long orderId);
    List<OrderResponse> findAllOrdersByEmployeeId(Long employeeId);
}
