package com.foodcourt.squaremallmanagment.application.handler.impl;

import com.foodcourt.squaremallmanagment.application.dto.request.DeliverOrderRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.NotificationRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.OrderCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.response.OrderResponse;
import com.foodcourt.squaremallmanagment.application.handler.IOrderHandler;
import com.foodcourt.squaremallmanagment.application.handler.helper.HelperClass;
import com.foodcourt.squaremallmanagment.application.mapper.impl.OrderRequestMapper;
import com.foodcourt.squaremallmanagment.domain.api.IOrderServicePort;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final HelperClass helperClass;

    @Override
    public void makeOrder(OrderCreateRequest orderCreateRequest) {
        OrderModel orderToMake = OrderRequestMapper.toOrderModel(orderCreateRequest);
        orderToMake.setUserDni(helperClass.getUserDni());
        orderServicePort.makeOrder(orderToMake);
    }

    @Override
    public void assignOrderToEmployee(Long orderId) {
        orderServicePort.assignOrderToEmployee(orderId,  helperClass.getUserDni());
    }

    @Override
    public List<OrderResponse> getOrdersByEmployee(String status, Integer page, Integer size) {
        return OrderRequestMapper.toOrderResponse(orderServicePort.getOrdersByEmployee( status, page, size,  helperClass.getUserDni()));
    }

    @Override
    public void changeOrderToReady(NotificationRequest notification, Long orderId) {
        orderServicePort.changeOrderToReady(OrderRequestMapper.toNotificationModel(notification), orderId,  helperClass.getUserDni()) ;
    }

    @Override
    public void deliverOrder(DeliverOrderRequest deliverOrder, Long orderId) {
        orderServicePort.deliverOrder(OrderRequestMapper.toDeliverOrderModel(deliverOrder),
                orderId,  helperClass.getUserDni());
    }

    @Override
    public void cancelOrder(Long orderId) {
        orderServicePort.cancelOrder(orderId,  helperClass.getUserDni());
    }


}
