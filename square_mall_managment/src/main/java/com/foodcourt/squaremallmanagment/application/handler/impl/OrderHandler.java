package com.foodcourt.squaremallmanagment.application.handler.impl;

import com.foodcourt.squaremallmanagment.application.dto.request.DeliverOrderRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.NotificationRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.OrderCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.response.OrderResponse;
import com.foodcourt.squaremallmanagment.application.handler.IOrderHandler;
import com.foodcourt.squaremallmanagment.application.handler.util.UtilClass;
import com.foodcourt.squaremallmanagment.application.mapper.impl.OrderRequestMapper;
import com.foodcourt.squaremallmanagment.domain.api.IOrderServicePort;
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

    @Override
    public void makeOrder(OrderCreateRequest orderCreateRequest) {
        orderServicePort.makeOrder(OrderRequestMapper.toOrderModel(orderCreateRequest), UtilClass.getUserDni());
    }

    @Override
    public void assignOrderToEmployee(Long orderId) {
        orderServicePort.assignOrderToEmployee(orderId, UtilClass.getUserDni());
    }

    @Override
    public List<OrderResponse> getOrdersByEmployee(String status, Integer page, Integer size) {
        return OrderRequestMapper.toOrderResponse(orderServicePort.getOrdersByEmployee( status, page, size, UtilClass.getUserDni()));
    }

    @Override
    public void changeOrderToReady(NotificationRequest notification, Long orderId) {
        orderServicePort.changeOrderToReady(OrderRequestMapper.toNotificationModel(notification), orderId, UtilClass.getUserDni()) ;
    }

    @Override
    public void deliverOrder(DeliverOrderRequest deliverOrder, Long orderId) {
        orderServicePort.deliverOrder(OrderRequestMapper.toDeliverOrderModel(deliverOrder),
                orderId, UtilClass.getUserDni());
    }

    @Override
    public void cancelOrder(Long orderId) {
        orderServicePort.cancelOrder(orderId);
    }


}
