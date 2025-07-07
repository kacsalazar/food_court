package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.exception.InvalidStateTransitionException;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.order.NotificationOrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModelReturn;
import com.foodcourt.squaremallmanagment.domain.model.UserModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderUpdateModel;
import com.foodcourt.squaremallmanagment.domain.spi.*;
import com.foodcourt.squaremallmanagment.domain.exception.InvalidOrderException;
import com.foodcourt.squaremallmanagment.domain.exception.OrderNotFoundException;
import com.foodcourt.squaremallmanagment.domain.usecase.util.StateEnum;
import com.foodcourt.squaremallmanagment.mocks.CreatorMocksUser;
import com.foodcourt.squaremallmanagment.mocks.CreatorOrderMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;
import static org.assertj.core.api.Assertions.*;

class OrderUseCaseTest {

    @Mock
    private IOrderPersistencePort orderPersistencePort;

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @Mock
    private IUserRestPort userClientPort;

    @Mock
    private ISendNotificationPort sendNotificationPort;

    @Mock
    private ITraceabilityPersistencePort traceabilityPersistencePort;

    @Mock
    private IEmployeeRestPort employeeRestPort;

    @InjectMocks
    private OrderUseCase orderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void makeOrder() {
        // Arrange
        OrderModel order = OrderModel.builder()
                .userDni("123")
                .restaurantId(1L)
                .dishes(List.of(OrderModel.Dish.builder()
                        .dishId(10L)
                        .quantity(2)
                        .build()))
                .build();

        when(userClientPort.ownerExists("123")).thenReturn(UserModel.builder().id(1L).build());
        when(dishPersistencePort.findDishById(10L)).thenReturn(DishModel.builder()
                .restaurantInfo(DishModel.RestaurantInfo.builder().idRestaurant(1L).build())
                .build());
        when(orderPersistencePort.findOrdersByIdUser(1L)).thenReturn(List.of());

        // Act
        orderUseCase.makeOrder(order);

        // Assert
        verify(orderPersistencePort).makeOrder(order);
        assertEquals(StateEnum.PENDING.name(), order.getStatus());
    }


}