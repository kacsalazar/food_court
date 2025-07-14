package com.foodcourt.traceabilitymanagement.infrastructure.out.restclient;

import com.foodcourt.traceabilitymanagement.domain.model.order.OrderModel;
import com.foodcourt.traceabilitymanagement.domain.model.order.OrderUpdateModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.List;

import static org.mockito.Mockito.*;

class OrderRestAdapterTest {

    private RestTemplate restTemplate;
    private OrderRestAdapter orderRestAdapter;
    private final String baseUrl = "http://localhost:8082/api/v1/order/";

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        orderRestAdapter = new OrderRestAdapter(restTemplate);
    }

    @Test
    void findOrderById_shouldReturnOrderUpdateModel() {
        Long orderId = 1L;
        String expectedUrl = baseUrl + "order/" + orderId;
        OrderUpdateModel mockOrder = new OrderUpdateModel();

        when(restTemplate.getForObject(expectedUrl, OrderUpdateModel.class)).thenReturn(mockOrder);

        OrderUpdateModel result = orderRestAdapter.findOrderById(orderId);

        assertNotNull(result);
        verify(restTemplate, times(2)).getForObject(expectedUrl, OrderUpdateModel.class);
    }

    @Test
    void findAllOrdersByEmployeeId_shouldReturnListOfOrders() {
        Long employeeId = 1L;
        String expectedUrl = baseUrl + "employee/" + employeeId;
        OrderModel[] orders = new OrderModel[]{new OrderModel(), new OrderModel()};

        when(restTemplate.getForObject(expectedUrl, OrderModel[].class)).thenReturn(orders);

        List<OrderModel> result = orderRestAdapter.findAllOrdersByEmployeeId(employeeId);

        assertEquals(2, result.size());
        verify(restTemplate).getForObject(expectedUrl, OrderModel[].class);
    }

    @Test
    void findAllOrdersByRestaurantId_shouldReturnListOfOrders() {
        Long restaurantId = 10L;
        String expectedUrl = baseUrl + "restaurant/" + restaurantId;
        OrderModel[] orders = new OrderModel[]{new OrderModel()};

        when(restTemplate.getForObject(expectedUrl, OrderModel[].class)).thenReturn(orders);

        List<OrderModel> result = orderRestAdapter.findAllOrdersByRestaurantId(restaurantId);

        assertEquals(1, result.size());
        verify(restTemplate).getForObject(expectedUrl, OrderModel[].class);
    }

    @Test
    void findAllOrdersByRestaurantId_shouldReturnEmptyListWhenNull() {
        Long restaurantId = 10L;
        String expectedUrl = baseUrl + "restaurant/" + restaurantId;

        when(restTemplate.getForObject(expectedUrl, OrderModel[].class)).thenReturn(null);

        List<OrderModel> result = orderRestAdapter.findAllOrdersByRestaurantId(restaurantId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(restTemplate).getForObject(expectedUrl, OrderModel[].class);
    }

}