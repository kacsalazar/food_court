package com.foodcourt.traceabilitymanagement.domain.usecase;

import static org.junit.jupiter.api.Assertions.*;

import com.foodcourt.traceabilitymanagement.domain.exception.OrdersNotFoundException;
import com.foodcourt.traceabilitymanagement.domain.exception.TraceabilityEmptyException;
import com.foodcourt.traceabilitymanagement.domain.model.EmployeeRankingModel;
import com.foodcourt.traceabilitymanagement.domain.model.TraceabilityModel;
import com.foodcourt.traceabilitymanagement.domain.model.order.OrderModel;
import com.foodcourt.traceabilitymanagement.domain.model.order.OrderUpdateModel;
import com.foodcourt.traceabilitymanagement.domain.model.restaurant.RestaurantModel;
import com.foodcourt.traceabilitymanagement.domain.model.user.EmployeeModel;
import com.foodcourt.traceabilitymanagement.domain.model.user.UserModel;
import com.foodcourt.traceabilitymanagement.domain.spi.*;
import com.foodcourt.traceabilitymanagement.domain.usecase.util.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

class TraceabilityUseCaseTest {

    @Mock
    private ITraceabilityPersistencePort traceabilityPersistencePort;

    @Mock
    private IOrderRestPort orderRestPort;

    @Mock
    private IUserRestPort userClientPort;

    @Mock
    private IRestaurantRestPort restaurantPersistencePort;

    @Mock
    private IEmployeeRestPort employeeRestPort;

    @InjectMocks
    private TraceabilityUseCase traceabilityUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTracesByOrderId_shouldReturnList() {
        Long orderId = 1L;
        String userDni = "123";
        OrderUpdateModel order = new OrderUpdateModel();
        order.setIdClient(1L);

        UserModel user = new UserModel();
        user.setId(1L);

        when(orderRestPort.findOrderById(orderId)).thenReturn(order);
        when(userClientPort.ownerExists(userDni)).thenReturn(user);
        when(traceabilityPersistencePort.findAllTracesByOrderId(orderId)).thenReturn(List.of(new TraceabilityModel()));

        List<TraceabilityModel> result = traceabilityUseCase.findAllTracesByOrderId(orderId, userDni);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void findAllTracesByOrderId_shouldThrowTraceabilityEmptyException() {
        Long orderId = 1L;
        String userDni = "123";
        OrderUpdateModel order = new OrderUpdateModel();
        order.setIdClient(1L);

        UserModel user = new UserModel();
        user.setId(1L);

        when(orderRestPort.findOrderById(orderId)).thenReturn(order);
        when(userClientPort.ownerExists(userDni)).thenReturn(user);
        when(traceabilityPersistencePort.findAllTracesByOrderId(orderId)).thenReturn(null);

        assertThrows(TraceabilityEmptyException.class, () ->
                traceabilityUseCase.findAllTracesByOrderId(orderId, userDni));
    }

    @Test
    void getOrdersProcessingTime_shouldThrowOrdersNotFoundException() {
        Long restaurantId = 1L;
        String userDni = "123";
        RestaurantModel restaurant = new RestaurantModel();
        restaurant.setIdOwner(1L);

        UserModel user = new UserModel();
        user.setId(1L);

        when(restaurantPersistencePort.findRestaurantById(restaurantId)).thenReturn(restaurant);
        when(userClientPort.ownerExists(userDni)).thenReturn(user);
        when(orderRestPort.findAllOrdersByRestaurantId(restaurantId)).thenReturn(List.of());

        assertThrows(OrdersNotFoundException.class, () ->
                traceabilityUseCase.getOrdersProcessingTime(restaurantId, userDni));
    }

    @Test
    void saveTraceability_shouldCallPort() {
        TraceabilityModel model = new TraceabilityModel();

        traceabilityUseCase.saveTraceability(model);

        verify(traceabilityPersistencePort).saveTraceability(model);
    }

    @Test
    void getOrderProcessingTime_shouldReturnFormattedDuration() {
        Long restaurantId = 1L;
        String userDni = "123";
        Long userId = 1L;
        Long orderId = 100L;

        UserModel user = new UserModel();
        user.setId(1L);

        // Mockear restaurante y validación de propietario
        RestaurantModel restaurant = new RestaurantModel();
        restaurant.setIdOwner(userId);
        when(restaurantPersistencePort.findRestaurantById(restaurantId)).thenReturn(restaurant);
        when(userClientPort.ownerExists(userDni)).thenReturn(user);

        // Mockear orden asociada al restaurante
        OrderModel order = new OrderModel();
        order.setOrderId(orderId);
        when(orderRestPort.findAllOrdersByRestaurantId(restaurantId)).thenReturn(List.of(order));

        // Mockear trazas de orden
        LocalDateTime start = LocalDateTime.now().minusMinutes(10);
        LocalDateTime end = LocalDateTime.now();

        TraceabilityModel startModel = new TraceabilityModel();
        startModel.setNewState(StatusEnum.IN_PROGRESS.name());
        startModel.setDate(start);

        TraceabilityModel endModel = new TraceabilityModel();
        endModel.setNewState(StatusEnum.DELIVERED.name());
        endModel.setDate(end);

        when(traceabilityPersistencePort.findAllTracesByOrderId(orderId)).thenReturn(List.of(startModel, endModel));

        String result = traceabilityUseCase.getOrdersProcessingTime(restaurantId, userDni).get(0);

        assertTrue(result.startsWith("Elapsed time of"));
    }

    @Test
    void getRankingForOrderByEmployeeId_shouldReturnRanking() {
        Long restaurantId = 1L;
        String userDni = "123";

        RestaurantModel restaurant = new RestaurantModel();
        restaurant.setIdOwner(1L);

        UserModel user = new UserModel();
        user.setId(1L);

        when(restaurantPersistencePort.findRestaurantById(restaurantId)).thenReturn(restaurant);
        when(userClientPort.ownerExists(userDni)).thenReturn(user);

        EmployeeModel employee = new EmployeeModel();
        employee.setId(10L);
        when(employeeRestPort.getEmployeesByRestaurantId(restaurantId)).thenReturn(List.of(employee));

        OrderModel order = new OrderModel();
        order.setOrderId(100L);
        order.setEmployeeId(10L);
        when(orderRestPort.findAllOrdersByEmployeeId(10L)).thenReturn(List.of(order));

        TraceabilityModel inProgress = new TraceabilityModel();
        inProgress.setNewState(StatusEnum.IN_PROGRESS.name());
        inProgress.setDate(LocalDateTime.now().minusMinutes(5));

        TraceabilityModel delivered = new TraceabilityModel();
        delivered.setNewState(StatusEnum.DELIVERED.name());
        delivered.setDate(LocalDateTime.now());

        when(traceabilityPersistencePort.findAllByOrderIdAndStatus(100L)).thenReturn(List.of(inProgress, delivered));

        List<EmployeeRankingModel> result = traceabilityUseCase.getRankingForOrderByEmployeeId(restaurantId, userDni);

        assertEquals(1, result.size());
        assertEquals(10L, result.get(0).getEmployeeId());
    }

    @Test
    void getOrderProcessingTime_shouldThrowTraceabilityEmptyException_whenMissingStates() {
            Long restaurantId = 1L;
            String userDni = "dni";
            Long userId = 1L;
            Long orderId = 100L;

            UserModel user = new UserModel();
            user.setId(1L);

            // Restaurante con mismo owner
            RestaurantModel restaurant = new RestaurantModel();
            restaurant.setIdOwner(userId);
            when(restaurantPersistencePort.findRestaurantById(restaurantId)).thenReturn(restaurant);

            // Usuario propietario
            when(userClientPort.ownerExists(userDni)).thenReturn(user);

            // Una orden asociada al restaurante
            OrderModel order = new OrderModel();
            order.setOrderId(orderId);
            when(orderRestPort.findAllOrdersByRestaurantId(restaurantId)).thenReturn(List.of(order));

            // Traza con estado no válido
            TraceabilityModel trace = new TraceabilityModel();
            trace.setNewState("OTHER");
            trace.setDate(LocalDateTime.now());
            when(traceabilityPersistencePort.findAllTracesByOrderId(orderId)).thenReturn(List.of(trace));

            assertThrows(TraceabilityEmptyException.class, () ->
                    traceabilityUseCase.getOrdersProcessingTime(restaurantId, userDni));
    }



}