package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModelReturn;
import com.foodcourt.squaremallmanagment.domain.model.UserModel;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.OrderEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.OrderVsDishEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.impl.OrderEntityMapper;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.IOrderRepository;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.IOrderVsDishRepository;
import com.foodcourt.squaremallmanagment.infrastructure.out.restclient.UserClientAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderAdapterTest {

    @Mock
    private UserClientAdapter userClientAdapter;
    @Mock
    private IOrderRepository orderRepository;
    @Mock
    private IOrderVsDishRepository orderDishRepository;

    @InjectMocks
    private OrderAdapter orderAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void makeOrderTest() {
        // Arrange
        OrderModel orderModel = mock(OrderModel.class);
        OrderModel.Dish dish = mock(OrderModel.Dish.class);
        List<OrderModel.Dish> dishes = List.of(dish);

        when(orderModel.getUserDni()).thenReturn("dni123");
        when(orderModel.getDishes()).thenReturn(dishes);

        UserModel userModel = new UserModel();
        userModel.setId(10L);
        when(userClientAdapter.ownerExists("dni123")).thenReturn(userModel);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

        OrderVsDishEntity orderVsDishEntity = new OrderVsDishEntity();
        // Simula el mapeo estático
        try (MockedStatic<OrderEntityMapper> mapperMock = mockStatic(OrderEntityMapper.class)) {
            mapperMock.when(() -> OrderEntityMapper.toOrderEntity(orderModel)).thenReturn(orderEntity);
            mapperMock.when(() -> OrderEntityMapper.toOrderDishEntity(dish, 1L)).thenReturn(orderVsDishEntity);

            // Act
            orderAdapter.makeOrder(orderModel);

            // Assert
            verify(orderRepository).save(orderEntity);
            verify(orderDishRepository).save(orderVsDishEntity);
            verify(userClientAdapter).ownerExists("dni123");
        }
    }

    @Test
    void findOrdersByIdUserTest() {
        Long userId = 5L;
        List<OrderEntity> orderEntities = Collections.emptyList();
        List<OrderModelReturn> expected = List.of(new OrderModelReturn());

        when(orderRepository.findOrdersByIdClient(userId)).thenReturn(orderEntities);

        try (MockedStatic<OrderEntityMapper> mapperMock = mockStatic(OrderEntityMapper.class)) {
            mapperMock.when(() -> OrderEntityMapper.toOrderModelReturn(orderEntities)).thenReturn(expected);

            List<OrderModelReturn> result = orderAdapter.findOrdersByIdUser(userId);

            assertEquals(expected, result);
            verify(orderRepository).findOrdersByIdClient(userId);
        }
    }

}