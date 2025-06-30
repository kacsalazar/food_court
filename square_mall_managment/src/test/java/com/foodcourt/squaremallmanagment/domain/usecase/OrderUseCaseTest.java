package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.exception.InvalidStateTransitionException;
import com.foodcourt.squaremallmanagment.domain.model.order.NotificationOrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModelReturn;
import com.foodcourt.squaremallmanagment.domain.model.UserModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderUpdateModel;
import com.foodcourt.squaremallmanagment.domain.spi.IOrderPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.ISendNotificationPort;
import com.foodcourt.squaremallmanagment.domain.spi.ITraceabilityPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IUserRestPort;
import com.foodcourt.squaremallmanagment.domain.exception.InvalidOrderException;
import com.foodcourt.squaremallmanagment.domain.exception.OrderNotFoundException;
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
    private IUserRestPort userClientPort;

    @Mock
    private ISendNotificationPort sendNotificationPort;

    @Mock
    private ITraceabilityPersistencePort traceabilityPersistencePort;

    @InjectMocks
    private OrderUseCase orderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void makeOrder() {
        // Arrange
        OrderModel orderModel = CreatorOrderMocks.createOrderModel(); // status vacío, userDni = "123"
        UserModel user = CreatorMocksUser.createUserModel(); // id = 1L, dni = "123"

        when(userClientPort.ownerExists("123")).thenReturn(user);
        when(orderPersistencePort.findOrdersByIdUser(1L)).thenReturn(Collections.emptyList());

        // Act
        orderUseCase.makeOrder(orderModel, "123");

        // Assert
        assertThat(orderModel.getStatus()).isEqualTo("PENDING");
        assertThat(orderModel.getId()).isEqualTo(1L);
        verify(orderPersistencePort).makeOrder(orderModel);
    }

    @Test
    void makeOrderInvalidOrder() {
        OrderModel orderModel = CreatorOrderMocks.createOrderModel();
        UserModel user = CreatorMocksUser.createUserModel();
        List<OrderModelReturn> existingOrders = List.of(new OrderModelReturn());

        when(userClientPort.ownerExists("123")).thenReturn(user);
        when(orderPersistencePort.findOrdersByIdUser(1L)).thenReturn(existingOrders);

        assertThatThrownBy(() -> orderUseCase.makeOrder(orderModel, "123"))
                .isInstanceOf(InvalidOrderException.class);

        verify(orderPersistencePort, never()).makeOrder(any());
    }

    @Test
    void assignOrderToEmployee() {
        // Arrange
        OrderUpdateModel order = CreatorOrderMocks.buildPendingOrderUpdateModel(); // status = "PENDING"
        UserModel employee = CreatorMocksUser.buildEmployeeModel(); // id = 2L
        UserModel client = CreatorMocksUser.buildUserModel();       // para getUserById (trazabilidad)

        when(orderPersistencePort.findOrderById(1L)).thenReturn(order);
        when(userClientPort.ownerExists("456")).thenReturn(employee);
        when(userClientPort.getUserById(order.getIdClient())).thenReturn(client);
        when(userClientPort.getUserById(employee.getId())).thenReturn(employee);

        // Act
        orderUseCase.assignOrderToEmployee(1L, "456");

        // Assert
        assertThat(order.getStatus()).isEqualTo("IN_PROGRESS");
        assertThat(order.getIdChef()).isEqualTo(employee.getId());
        verify(orderPersistencePort).updateOrder(order);
        verify(traceabilityPersistencePort).saveTraceability(any());
    }

    @Test
    void invalidStateTransitionException() {
        OrderUpdateModel order = CreatorOrderMocks.buildOrderUpdateModelWithStatus("COMPLETED");

        when(orderPersistencePort.findOrderById(1L)).thenReturn(order);

        assertThatThrownBy(() -> orderUseCase.assignOrderToEmployee(1L, "456"))
                .isInstanceOf(InvalidStateTransitionException.class);

        verify(orderPersistencePort, never()).updateOrder(any());
    }

    @Test
    void orderNotFoundException() {
        when(orderPersistencePort.findOrderById(1L)).thenReturn(null);

        assertThatThrownBy(() -> orderUseCase.assignOrderToEmployee(1L, "456"))
                .isInstanceOf(OrderNotFoundException.class);
    }

    @Test
    void getOrdersByEmployee_ok() {
        String status = "PENDING";
        int page = 0, size = 5;
        String dniEmployee = "dniEmp";
        UserModel employee = new UserModel();
        employee.setId(2L);
        List<OrderModel> orders = List.of(new OrderModel());

        when(userClientPort.ownerExists(dniEmployee)).thenReturn(employee);
        when(orderPersistencePort.getOrdersByEmployee(status, page, size, 2L)).thenReturn(orders);

        List<OrderModel> result = orderUseCase.getOrdersByEmployee(status, page, size, dniEmployee);

        assertEquals(orders, result);
    }

    @Test
    void changeOrderToReady() {
        // Arrange
        OrderUpdateModel order = CreatorOrderMocks.buildOrderUpdateModelWithStatus("IN_PROGRESS");
        UserModel client = CreatorMocksUser.buildUserModel(); // Tiene phoneNumber

        NotificationOrderModel notification = NotificationOrderModel.builder()
                .messageBody("Tu orden está lista. ")
                .build();

        when(orderPersistencePort.findOrderById(1L)).thenReturn(order);
        when(userClientPort.getUserById(order.getIdClient())).thenReturn(client);
        when(userClientPort.getUserById(2L)).thenReturn(CreatorMocksUser.buildEmployeeModel());

        // Act
        orderUseCase.changeOrderToReady(notification, 1L, "2");

        // Assert
        assertThat(order.getStatus()).isEqualTo("COMPLETED");
        assertThat(order.getSecurityPin()).isNotNull();

        verify(orderPersistencePort).updateOrder(order);
        verify(sendNotificationPort).sendMessage(
                eq(client.getPhoneNumber()),
                contains("Pin: ")
        );
        verify(traceabilityPersistencePort).saveTraceability(any());
    }

    @Test
    void changeOrderToReady_orderNotFound_throwsException() {
        when(orderPersistencePort.findOrderById(anyLong())).thenReturn(null);

        assertThrows(OrderNotFoundException.class, () -> orderUseCase.changeOrderToReady(new NotificationOrderModel(), 1L, "2"));
    }
}