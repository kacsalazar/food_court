package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModelReturn;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderUpdateModel;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.OrderEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.OrderVsDishEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.IOrderRepository;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.IOrderVsDishRepository;
import com.foodcourt.squaremallmanagment.infrastructure.out.restclient.UserRestAdapter;
import com.foodcourt.squaremallmanagment.mocks.CreatorAdapterMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderAdapterTest {

    @Mock
    private UserRestAdapter userRestAdapter;
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
        OrderModel orderModel = CreatorAdapterMocks.createOrderModel();
        when(orderRepository.save(any(OrderEntity.class))).thenAnswer(invocation -> {
            OrderEntity saved = invocation.getArgument(0);
            saved.setId(123L);
            return saved;
        });

        // Act
        orderAdapter.makeOrder(orderModel);

        // Assert
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
        verify(orderDishRepository, times(1)).save(any(OrderVsDishEntity.class));
    }

    @Test
    void findOrdersByIdUserTest() {
        List<OrderEntity> mockOrders = List.of(
                OrderEntity.builder()
                        .id(1L)
                        .idClient(10L)
                        .idChef(2L)
                        .status("PENDING")
                        .orderDate(Date.from(Instant.now()))
                        .build()
        );

        when(orderRepository.findOrdersByIdClient(10L)).thenReturn(mockOrders);

        // Act
        List<OrderModelReturn> result = orderAdapter.findOrdersByIdUser(10L);

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEmployeeId()).isEqualTo(2L);
    }

    @Test
    void findOrderByIdTest() {
        // Arrange
        OrderEntity order = OrderEntity.builder()
                .id(55L)
                .idClient(999L)
                .idChef(5L)
                .status("PENDING")
                .orderDate(Date.from(Instant.now()))
                .build();

        when(orderRepository.findById(55L)).thenReturn(Optional.of(order));

        // Act
        OrderUpdateModel result = orderAdapter.findOrderById(55L);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(55L);
    }

    @Test
    void should_throw_exception_when_order_not_found_by_id() {
        when(orderRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderAdapter.findOrderById(999L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Order not found with id");
    }

    @Test
    void updateOrderTest() {
        OrderUpdateModel model = OrderUpdateModel.builder()
                .id(88L)
                .idClient(1L)
                .idChef(3L)
                .status("DELIVERED")
                .orderDate(Date.from(Instant.now()))
                .build();

        orderAdapter.updateOrder(model);

        verify(orderRepository).save(any(OrderEntity.class));
    }

    @Test
    void getOrdersByEmployeeTest() {
        // Arrange
        List<OrderEntity> orders = List.of(
                OrderEntity.builder()
                        .id(1L)
                        .idChef(5L)
                        .idClient(7L)
                        .status("PENDING")
                        .orderDate(Date.from(Instant.now()))
                        .build()
        );

        List<OrderVsDishEntity> dishes = List.of(
                OrderVsDishEntity.builder()
                        .idOrder(1L)
                        .idDish(200L)
                        .quantity(1)
                        .build()
        );

        when(orderRepository.findOrdersByStatus(5L, "PENDING", 10, 0)).thenReturn(orders);
        when(orderDishRepository.findByOrderId(1L)).thenReturn(dishes);

        // Act
        List<OrderModel> result = orderAdapter.getOrdersByEmployee("PENDING", 0, 10, 5L);

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getDishes()).hasSize(1);
    }
}