package com.foodcourt.squaremallmanagment.application.handler.impl;

import com.foodcourt.squaremallmanagment.application.dto.response.EmployeeRankingResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.TraceabilityResponse;
import com.foodcourt.squaremallmanagment.application.handler.helper.HelperClass;
import com.foodcourt.squaremallmanagment.domain.api.ITraceabilityServicePort;
import com.foodcourt.squaremallmanagment.domain.model.EmployeeRankingModel;
import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TraceabilityHandlerTest {

    @Mock
    private ITraceabilityServicePort traceabilityServicePort;

    @Mock
    private HelperClass helperClass;

    @InjectMocks
    private TraceabilityHandler traceabilityHandler;

    private final String dni = "12345678";

    @BeforeEach
    void setUp() {
        when(helperClass.getUserDni()).thenReturn(dni);
        //MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTracesByOrderId_shouldReturnTraceabilityResponses() {
        Long orderId = 1L;

        List<TraceabilityModel> traceabilityModels = List.of(
                TraceabilityModel.builder().orderId(orderId).build(),
                TraceabilityModel.builder().orderId(orderId).build()
        );

        when(traceabilityServicePort.findAllTracesByOrderId(orderId, dni)).thenReturn(traceabilityModels);

        List<TraceabilityResponse> result = traceabilityHandler.findAllTracesByOrderId(orderId);

        assertEquals(2, result.size());
        verify(traceabilityServicePort).findAllTracesByOrderId(orderId, dni);
    }

    @Test
    void getOrderProcessingTime_shouldReturnStringTime() {
        Long orderId = 2L;
        String expectedTime = "30 minutes";

        when(traceabilityServicePort.getOrderProcessingTime(orderId, dni)).thenReturn(expectedTime);

        String result = traceabilityHandler.getOrderProcessingTime(orderId);

        assertEquals(expectedTime, result);
        verify(traceabilityServicePort).getOrderProcessingTime(orderId, dni);
    }

    @Test
    void getRankingForOrderByEmployeeId_shouldReturnEmployeeRankingResponses() {
        Long restaurantId = 3L;

        List<EmployeeRankingModel> rankingModels = List.of(
                new EmployeeRankingModel(), new EmployeeRankingModel()
        );

        when(traceabilityServicePort.getRankingForOrderByEmployeeId(restaurantId, dni)).thenReturn(rankingModels);

        List<EmployeeRankingResponse> result = traceabilityHandler.getRankingForOrderByEmployeeId(restaurantId);

        assertEquals(2, result.size());
        verify(traceabilityServicePort).getRankingForOrderByEmployeeId(restaurantId, dni);
    }

}