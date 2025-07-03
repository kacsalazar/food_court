package com.foodcourt.squaremallmanagment.application.handler.impl;

import com.foodcourt.squaremallmanagment.application.dto.request.DeliverOrderRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.NotificationRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.OrderCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.response.OrderResponse;
import com.foodcourt.squaremallmanagment.application.handler.helper.HelperClass;
import com.foodcourt.squaremallmanagment.application.mapper.impl.OrderRequestMapper;
import com.foodcourt.squaremallmanagment.domain.api.IOrderServicePort;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import com.foodcourt.squaremallmanagment.mocks.CreatorOrderMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class OrderHandlerTest {


    @Mock
    private IOrderServicePort orderServicePort;

    @InjectMocks
    private OrderHandler orderHandler;

    @Mock
    private HelperClass helperClass;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldMakeOrder() {
        OrderCreateRequest request = CreatorOrderMocks.createOrderCreateRequestMock();

        when(helperClass.getUserDni()).thenReturn("1234");

        orderHandler.makeOrder(request);

        verify(orderServicePort).makeOrder(argThat(order ->
                order.getUserDni().equals("1234")
        ));
    }

    @Test
    void assignOrderToEmployee() {
        Long orderId = 1L;

        when(helperClass.getUserDni()).thenReturn("1234");

        orderHandler.assignOrderToEmployee(orderId);

        verify(orderServicePort).assignOrderToEmployee(orderId, "1234");
    }

    @Test
    void shouldGetOrdersByEmployee() {
        String status = "PENDING";
        int page = 0, size = 10;
        //List<OrderResponse> expected = List.of(new OrderResponse());

        List<OrderResponse> result = orderHandler.getOrdersByEmployee(status, page, size);

        assertNotNull(result);
    }

    @Test
    void changeOrderToReady() {
        Long orderId = 1L;
        NotificationRequest notification = new NotificationRequest();

        when(helperClass.getUserDni()).thenReturn("1234");

        orderHandler.changeOrderToReady(notification, orderId);

        verify(orderServicePort).changeOrderToReady(
                any(), eq(orderId), eq("1234"));
    }

    @Test
    void deliverOrder() {
        Long orderId = 2L;
        DeliverOrderRequest deliverRequest = new DeliverOrderRequest();

        when(helperClass.getUserDni()).thenReturn("1234");

        orderHandler.deliverOrder(deliverRequest, orderId);

        verify(orderServicePort).deliverOrder(any(), eq(orderId), eq("1234"));
    }

    @Test
    void cancelOrder() {
        Long orderId = 3L;

        when(helperClass.getUserDni()).thenReturn("1234");

        orderHandler.cancelOrder(orderId);

        verify(orderServicePort).cancelOrder(orderId, "1234");
    }

}