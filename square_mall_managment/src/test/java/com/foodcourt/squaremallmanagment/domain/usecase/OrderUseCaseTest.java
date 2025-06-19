package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.exception.ConstantException;
import com.foodcourt.squaremallmanagment.domain.exception.DomainException;
import com.foodcourt.squaremallmanagment.domain.model.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.OrderModelReturn;
import com.foodcourt.squaremallmanagment.domain.model.UserModel;
import com.foodcourt.squaremallmanagment.domain.spi.IOrderPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IUserClientPort;
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

class OrderUseCaseTest {


    @Mock
    private IOrderPersistencePort orderPersistencePort;
    @Mock
    private IUserClientPort userClientPort;

    @InjectMocks
    private OrderUseCase orderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void makeOrderTest() {
        OrderModel orderModel = mock(OrderModel.class);
        when(orderModel.getUserDni()).thenReturn("dni123");

        UserModel userModel = new UserModel();
        userModel.setId(1L);
        when(userClientPort.ownerExists("dni123")).thenReturn(userModel);
        when(orderPersistencePort.findOrdersByIdUser(1L)).thenReturn(Collections.emptyList());

        orderUseCase.makeOrder(orderModel);

        verify(orderPersistencePort).makeOrder(orderModel);
    }

    @Test
    void makeOrderThrowsExceptionTest() {
        OrderModel orderModel = mock(OrderModel.class);
        when(orderModel.getUserDni()).thenReturn("dni123");

        UserModel userModel = new UserModel();
        userModel.setId(1L);
        when(userClientPort.ownerExists("dni123")).thenReturn(userModel);
        when(orderPersistencePort.findOrdersByIdUser(1L)).thenReturn(List.of(new OrderModelReturn()));

        DomainException ex = assertThrows(DomainException.class, () -> {
            orderUseCase.makeOrder(orderModel);
        });

        assertEquals(ConstantException.INVALID_ORDER, ex.getMessage());
        verify(orderPersistencePort, never()).makeOrder(any());
    }

}