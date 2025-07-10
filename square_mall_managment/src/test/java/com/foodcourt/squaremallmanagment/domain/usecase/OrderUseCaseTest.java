package com.foodcourt.squaremallmanagment.domain.usecase;


import com.foodcourt.squaremallmanagment.domain.exception.InvalidStateTransitionException;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.order.*;
import com.foodcourt.squaremallmanagment.domain.model.UserModel;
import com.foodcourt.squaremallmanagment.domain.spi.*;
import com.foodcourt.squaremallmanagment.domain.exception.InvalidOrderException;
import com.foodcourt.squaremallmanagment.domain.exception.OrderNotFoundException;
import com.foodcourt.squaremallmanagment.domain.usecase.util.StatusEnum;
import com.foodcourt.squaremallmanagment.mocks.CreatorMocksUser;
import com.foodcourt.squaremallmanagment.mocks.CreatorOrderMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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

    @InjectMocks
    private OrderUseCase orderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void makeOrder() {

        // Arrange
        String dni = "123456789";
        Long userId = 1L;
        Long restaurantId = 10L;
        Long dishId = 100L;

        // Mock del usuario que hace la orden
        UserModel user = UserModel.builder()
                .id(userId)
                .dni(dni)
                .build();
        when(userClientPort.ownerExists(dni)).thenReturn(user);

        // Mock del plato para validar el restaurante
        DishModel dish = DishModel.builder()
                .restaurantInfo(
                        DishModel.RestaurantInfo.builder()
                                .idRestaurant(restaurantId)
                                .build())
                .build();
        when(dishPersistencePort.findDishById(dishId)).thenReturn(dish);

        // No hay órdenes previas
        when(orderPersistencePort.findOrdersByIdUser(userId)).thenReturn(List.of());

        // Crear el modelo de la orden
        OrderModel order = OrderModel.builder()
                .userDni(dni)
                .restaurantId(restaurantId)
                .dishes(List.of(OrderModel.Dish.builder().dishId(dishId).quantity(1).build()))
                .build();

        // Act
        orderUseCase.makeOrder(order);

        // Assert
        verify(orderPersistencePort).makeOrder(order, userId);
        assertEquals(StatusEnum.PENDING.name(), order.getStatus());
        assertNotNull(order.getOrderDate());
    }

   @Test
    void should_change_order_to_ready_and_send_notification() {
        Long orderId = 1L;
        String employeeDni = "1234567890";
        NotificationOrderModel notification = new NotificationOrderModel();
        notification.setMessageBody("Order is ready: ");

        OrderUpdateModel order = new OrderUpdateModel();
        order.setId(orderId);
        order.setIdClient(10L);
        order.setIdChef(20L);
        order.setStatus(StatusEnum.IN_PROGRESS.name());

        UserModel user = new UserModel();
        user.setId(20L);
        user.setDni(employeeDni);

        UserModel customer = new UserModel();
        customer.setPhoneNumber("5551234567");

        when(orderPersistencePort.findOrderById(orderId)).thenReturn(order);
        when(userClientPort.ownerExists(employeeDni)).thenReturn(user);
        when(userClientPort.getUserById(order.getIdClient())).thenReturn(customer);
        when(userClientPort.getUserById(order.getIdChef())).thenReturn(user);

        orderUseCase.changeOrderToReady(notification, orderId, employeeDni);

        assertThat(order.getStatus()).isEqualTo(StatusEnum.COMPLETED.name());
        assertThat(order.getSecurityPin()).isNotNull();

        verify(orderPersistencePort).updateOrder(order);
        verify(sendNotificationPort).sendMessage(eq("5551234567"), contains("Pin: "));
        verify(traceabilityPersistencePort).saveTraceability(any());
    }

    @Test
    void should_deliver_order_if_pin_matches_and_status_completed() {
        Long orderId = 1L;
        String employeeDni = "1234567890";

        DeliverOrderModel deliverOrderModel = new DeliverOrderModel();
        deliverOrderModel.setSecurityPin("1234");

        OrderUpdateModel order = new OrderUpdateModel();
        order.setId(orderId);
        order.setIdChef(20L);
        order.setIdClient(10L);
        order.setStatus(StatusEnum.COMPLETED.name());
        order.setSecurityPin("1234");

        UserModel employee = new UserModel();
        employee.setId(20L);
        employee.setDni(employeeDni);

        UserModel customer = new UserModel();

        when(orderPersistencePort.findOrderById(orderId)).thenReturn(order);
        when(userClientPort.ownerExists(employeeDni)).thenReturn(employee);
        when(userClientPort.getUserById(order.getIdClient())).thenReturn(customer);
        when(userClientPort.getUserById(order.getIdChef())).thenReturn(employee);

        orderUseCase.deliverOrder(deliverOrderModel, orderId, employeeDni);

        assertThat(order.getStatus()).isEqualTo(StatusEnum.DELIVERED.name());
        verify(orderPersistencePort).updateOrder(order);
        verify(traceabilityPersistencePort).saveTraceability(any());
    }

    @Test
    void should_cancel_order_when_pending_and_belongs_to_user() {
        Long orderId = 1L;
        String customerDni = "11112222";

        OrderUpdateModel order = new OrderUpdateModel();
        order.setId(orderId);
        order.setStatus(StatusEnum.PENDING.name());
        order.setIdClient(100L);

        UserModel customer = new UserModel();
        customer.setId(100L);
        customer.setDni(customerDni);

        when(orderPersistencePort.findOrderById(orderId)).thenReturn(order);
        when(userClientPort.ownerExists(customerDni)).thenReturn(customer);
        when(userClientPort.getUserById(order.getIdClient())).thenReturn(customer);
        when(userClientPort.getUserById(order.getIdChef())).thenReturn(new UserModel());

        orderUseCase.cancelOrder(orderId, customerDni);

        assertThat(order.getStatus()).isEqualTo(StatusEnum.CANCELLED.name());
        verify(orderPersistencePort).updateOrder(order);
        verify(traceabilityPersistencePort).saveTraceability(any());
    }
}