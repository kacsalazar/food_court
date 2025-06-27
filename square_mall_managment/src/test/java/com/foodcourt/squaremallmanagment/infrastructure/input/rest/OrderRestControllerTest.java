package com.foodcourt.squaremallmanagment.infrastructure.input.rest;

import com.foodcourt.squaremallmanagment.application.dto.request.NotificationRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.OrderCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.response.OrderResponse;
import com.foodcourt.squaremallmanagment.application.handler.IOrderHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderRestControllerTest {


    @Mock
    private IOrderHandler orderHandler;

    @InjectMocks
    private OrderRestController orderRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void makeOrderTest() {
        OrderCreateRequest request = new OrderCreateRequest();

        ResponseEntity<Void> response = orderRestController.makeOrder(request);

        verify(orderHandler, times(1)).makeOrder(request);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void assignOrderToEmployeeTest() {
        Long orderId = 1L;

        ResponseEntity<Void> response = orderRestController.assignOrderToEmployee(orderId);

        verify(orderHandler, times(1)).assignOrderToEmployee(orderId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getOrdersByEmployeeTest() {
        String status = "PENDING";
        int page = 0, size = 10;
        List<OrderResponse> orders = Collections.singletonList(new OrderResponse());
        when(orderHandler.getOrdersByEmployee(status, page, size)).thenReturn(orders);

        ResponseEntity<List<OrderResponse>> response = orderRestController.getOrdersByEmployee(status, page, size);

        verify(orderHandler, times(1)).getOrdersByEmployee(status, page, size);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
    }

    @Test
    void changeOrderToReadyTest() {
        Long orderId = 1L;
        NotificationRequest notification = new NotificationRequest();

        ResponseEntity<Void> response = orderRestController.changeOrderToReady(orderId, notification);

        verify(orderHandler, times(1)).changeOrderToReady(notification, orderId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }
}