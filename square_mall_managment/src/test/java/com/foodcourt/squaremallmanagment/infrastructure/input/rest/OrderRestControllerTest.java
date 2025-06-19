package com.foodcourt.squaremallmanagment.infrastructure.input.rest;

import com.foodcourt.squaremallmanagment.application.dto.request.OrderCreateRequest;
import com.foodcourt.squaremallmanagment.application.handler.IOrderHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

}