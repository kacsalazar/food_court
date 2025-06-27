package com.foodcourt.squaremallmanagment.application.handler.impl;

import com.foodcourt.squaremallmanagment.application.dto.request.OrderCreateRequest;
import com.foodcourt.squaremallmanagment.application.mapper.impl.OrderRequestMapper;
import com.foodcourt.squaremallmanagment.domain.api.IOrderServicePort;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

class OrderHandlerTest {


    @Mock
    private IOrderServicePort orderServicePort;

    @InjectMocks
    private OrderHandler orderHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void makeOrderTest() {
        OrderCreateRequest request = new OrderCreateRequest();
        OrderModel orderModel = new OrderModel();

        try (MockedStatic<OrderRequestMapper> mapperMock = mockStatic(OrderRequestMapper.class)) {
            mapperMock.when(() -> OrderRequestMapper.toOrderModel(request)).thenReturn(orderModel);

            orderHandler.makeOrder(request);

            //verify(orderServicePort).makeOrder(orderModel);
        }
    }

}