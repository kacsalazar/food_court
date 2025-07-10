package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.exception.NotPermissionException;
import com.foodcourt.squaremallmanagment.domain.exception.RestaurantNotFoundException;
import com.foodcourt.squaremallmanagment.domain.model.EmployeeModel;
import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;
import com.foodcourt.squaremallmanagment.domain.model.UserModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderUpdateModel;
import com.foodcourt.squaremallmanagment.domain.model.restaurant.RestaurantModel;
import com.foodcourt.squaremallmanagment.domain.spi.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foodcourt.squaremallmanagment.domain.usecase.util.StatusEnum;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class TraceabilityUseCaseTest {

    @Mock
    private ITraceabilityPersistencePort traceabilityPersistencePort;

    @Mock
    private IOrderPersistencePort orderPersistencePort;

    @Mock
    private IUserRestPort userClientPort;

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private IEmployeeRestPort employeeRestPort;

    @InjectMocks
    private TraceabilityUseCase traceabilityUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllTracesByOrderId_Success() {
        Long orderId = 1L;
        String userDni = "123";

        OrderUpdateModel order = new OrderUpdateModel();
        order.setIdClient(10L);

        UserModel user = UserModel.builder()
                .id(10L)
                .dni(userDni)
                .build();

        when(orderPersistencePort.findOrderById(orderId)).thenReturn(order);
        when(userClientPort.ownerExists(userDni)).thenReturn(user);
        when(traceabilityPersistencePort.findAllTracesByOrderId(orderId)).thenReturn(List.of(new TraceabilityModel()));

        List<TraceabilityModel> result = traceabilityUseCase.findAllTracesByOrderId(orderId, userDni);

        assertEquals(1, result.size());
        verify(traceabilityPersistencePort).findAllTracesByOrderId(orderId);
    }

    @Test
    void testGetOrderProcessingTime_Success() {
        Long orderId = 1L;
        String userDni = "321";
        Long ownerId = 5L;

        OrderUpdateModel order = new OrderUpdateModel();
        order.setIdRestaurant(100L);
        RestaurantModel restaurant = new RestaurantModel();
        restaurant.setIdOwner(ownerId);

        TraceabilityModel start = TraceabilityModel.builder()
                .newState(StatusEnum.IN_PROGRESS.name())
                .date(LocalDateTime.now().minusMinutes(45))
                .build();

        TraceabilityModel end = TraceabilityModel.builder()
                .newState(StatusEnum.DELIVERED.name())
                .date(LocalDateTime.now())
                .build();

        UserModel user = UserModel.builder()
                .id(ownerId)
                .dni(userDni)
                .build();

        when(orderPersistencePort.findOrderById(orderId)).thenReturn(order);
        when(restaurantPersistencePort.findRestaurantById(100L)).thenReturn(restaurant);
        when(userClientPort.ownerExists(userDni)).thenReturn(user);
        when(traceabilityPersistencePort.findAllTracesByOrderId(orderId)).thenReturn(List.of(start, end));

        String result = traceabilityUseCase.getOrderProcessingTime(orderId, userDni);

        assertTrue(result.contains("Elapsed time of"));
    }

    @Test
    void testGetRankingForOrderByEmployeeId_Success() {
        Long restaurantId = 1L;
        String ownerDni = "999";
        Long ownerId = 50L;

        UserModel user = UserModel.builder()
                .id(ownerId)
                .dni(ownerDni)
                .build();

        RestaurantModel restaurant = new RestaurantModel();
        restaurant.setIdOwner(ownerId);
        when(restaurantPersistencePort.findRestaurantById(restaurantId)).thenReturn(restaurant);
        when(userClientPort.ownerExists(ownerDni)).thenReturn(user);

        EmployeeModel employee = new EmployeeModel();
        employee.setId(200L);
        employee.setEmployeeRestaurantId(restaurantId);
        when(employeeRestPort.getEmployeesByRestaurantId(restaurantId)).thenReturn(List.of(employee));

        OrderModel order = new OrderModel();
        order.setId(1L);
        order.setEmployeeId(200L);
        when(orderPersistencePort.findAllOrdersByEmployeeId(200L)).thenReturn(List.of(order));

        TraceabilityModel inProgress = new TraceabilityModel();
        inProgress.setNewState(StatusEnum.IN_PROGRESS.name());
        inProgress.setDate(LocalDateTime.now().minusMinutes(30));

        TraceabilityModel delivered = new TraceabilityModel();
        delivered.setNewState(StatusEnum.DELIVERED.name());
        delivered.setDate(LocalDateTime.now());

        when(traceabilityPersistencePort.findAllByOrderIdAndStatus(1L)).thenReturn(List.of(inProgress, delivered));

        var result = traceabilityUseCase.getRankingForOrderByEmployeeId(restaurantId, ownerDni);

        assertEquals(1, result.size());
        assertEquals(200L, result.get(0).getEmployeeId());
        assertTrue(result.get(0).getAverageSeconds() > 0);
    }

    @Test
    void testValidateOrderUser_Throws_NotPermissionException() {
        Long orderId = 1L;
        String userDni = "321";

        when(orderPersistencePort.findOrderById(orderId)).thenReturn(null);

        assertThrows(NotPermissionException.class,
                () -> traceabilityUseCase.findAllTracesByOrderId(orderId, userDni));
    }

    @Test
    void testValidateOwnerRestaurant_Throws_RestaurantNotFoundException() {
        when(restaurantPersistencePort.findRestaurantById(any())).thenReturn(null);

        assertThrows(RestaurantNotFoundException.class,
                () -> traceabilityUseCase.getRankingForOrderByEmployeeId(1L, "123"));
    }
}