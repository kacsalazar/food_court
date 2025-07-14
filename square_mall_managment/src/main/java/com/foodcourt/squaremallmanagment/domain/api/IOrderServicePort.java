package com.foodcourt.squaremallmanagment.domain.api;

import com.foodcourt.squaremallmanagment.application.dto.response.OrderResponse;
import com.foodcourt.squaremallmanagment.domain.model.order.DeliverOrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.NotificationOrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderUpdateModel;

import java.util.List;

public interface IOrderServicePort {

    void makeOrder(OrderModel orderModel);
    void assignOrderToEmployee(Long orderId, String employeeDni);
    List<OrderModel> getOrdersByEmployee(String status, Integer page, Integer size, String dniEmployee);
    void changeOrderToReady(NotificationOrderModel notificationOrderModel, Long orderId ,String employeeDni);
    void deliverOrder(DeliverOrderModel deliverOrderModel, Long orderId, String employeeDni);
    void cancelOrder(Long orderId, String customerDni);
    OrderUpdateModel findOrderById(Long orderId);
    List<OrderModel> findAllOrdersByEmployeeId(Long employeeId);
    List<OrderModel> findAllOrdersByRestaurantId(Long restaurantId);
}
