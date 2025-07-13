package com.foodcourt.traceabilitymanagement.application.handler.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.foodcourt.traceabilitymanagement.application.dto.request.TraceabilityRequest;
import com.foodcourt.traceabilitymanagement.application.dto.response.DurationTimeResponse;
import com.foodcourt.traceabilitymanagement.application.dto.response.EmployeeRankingResponse;
import com.foodcourt.traceabilitymanagement.application.dto.response.TraceabilityResponse;
import com.foodcourt.traceabilitymanagement.application.handler.helper.HelperClass;
import com.foodcourt.traceabilitymanagement.domain.api.ITraceabilityServicePort;
import com.foodcourt.traceabilitymanagement.domain.model.EmployeeRankingModel;
import com.foodcourt.traceabilitymanagement.domain.model.TraceabilityModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TraceabilityHandlerTest {

    @Mock
    private ITraceabilityServicePort traceabilityServicePort;

    @Mock
    private HelperClass helperClass;

    @InjectMocks
    private TraceabilityHandler traceabilityHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        traceabilityHandler = new TraceabilityHandler(traceabilityServicePort, helperClass);
    }

    @Test
    void findAllTracesByOrderId() {
        Long orderId = 1L;
        String userDni = "dni123";

        TraceabilityModel model = TraceabilityModel.builder().orderId(orderId).newState("DELIVERED").build();
        when(helperClass.getUserDni()).thenReturn(userDni);
        when(traceabilityServicePort.findAllTracesByOrderId(orderId, userDni)).thenReturn(List.of(model));

        List<TraceabilityResponse> result = traceabilityHandler.findAllTracesByOrderId(orderId);

        assertEquals(1, result.size());
        assertEquals("DELIVERED", result.get(0).getNewState());
    }

    @Test
    void getOrdersProcessingTime() {
        Long restaurantId = 1L;
        String userDni = "dni123";
        String duration = "2 hours";

        when(helperClass.getUserDni()).thenReturn(userDni);
        when(traceabilityServicePort.getOrdersProcessingTime(restaurantId, userDni)).thenReturn(List.of(duration));

        List<DurationTimeResponse> result = traceabilityHandler.getOrdersProcessingTime(restaurantId);

        assertEquals(1, result.size());
        assertEquals("2 hours", result.get(0).getDurationTime());
    }

    @Test
    void getRankingForOrderByEmployeeId() {
        Long restaurantId = 1L;
        String userDni = "dni123";
        EmployeeRankingModel model = new EmployeeRankingModel(5L, 123.0);

        when(helperClass.getUserDni()).thenReturn(userDni);
        when(traceabilityServicePort.getRankingForOrderByEmployeeId(restaurantId, userDni)).thenReturn(List.of(model));

        List<EmployeeRankingResponse> result = traceabilityHandler.getRankingForOrderByEmployeeId(restaurantId);

        assertEquals(1, result.size());
        assertEquals(5L, result.get(0).getEmployeeId());
    }

    @Test
    void saveTraceability() {
        TraceabilityRequest request = new TraceabilityRequest();
        traceabilityHandler.saveTraceability(request);
        verify(traceabilityServicePort).saveTraceability(any());
    }

}