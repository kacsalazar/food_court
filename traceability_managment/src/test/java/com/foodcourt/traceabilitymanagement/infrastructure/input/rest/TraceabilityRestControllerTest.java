package com.foodcourt.traceabilitymanagement.infrastructure.input.rest;

import static org.junit.jupiter.api.Assertions.*;

import com.foodcourt.traceabilitymanagement.application.dto.request.TraceabilityRequest;
import com.foodcourt.traceabilitymanagement.application.dto.response.DurationTimeResponse;
import com.foodcourt.traceabilitymanagement.application.dto.response.EmployeeRankingResponse;
import com.foodcourt.traceabilitymanagement.application.dto.response.TraceabilityResponse;
import com.foodcourt.traceabilitymanagement.application.handler.ITraceabilityHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
class TraceabilityRestControllerTest {

    @Mock
    private ITraceabilityHandler traceabilityHandler;

    @InjectMocks
    private TraceabilityRestController traceabilityRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTraceabilityByOrderId() {
        Long orderId = 1L;
        List<TraceabilityResponse> expectedResponse = List.of(new TraceabilityResponse());
        when(traceabilityHandler.findAllTracesByOrderId(orderId)).thenReturn(expectedResponse);

        ResponseEntity<List<TraceabilityResponse>> response = traceabilityRestController.getTraceabilityByOrderId(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void getOrdersProcessingTime() {
        Long restaurantId = 2L;
        List<DurationTimeResponse> expectedResponse = List.of(new DurationTimeResponse());
        when(traceabilityHandler.getOrdersProcessingTime(restaurantId)).thenReturn(expectedResponse);

        ResponseEntity<List<DurationTimeResponse>> response = traceabilityRestController.getOrdersProcessingTime(restaurantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void getRankingForOrderByEmployeeId() {
        Long restaurantId = 3L;
        List<EmployeeRankingResponse> expectedResponse = List.of(new EmployeeRankingResponse());
        when(traceabilityHandler.getRankingForOrderByEmployeeId(restaurantId)).thenReturn(expectedResponse);

        ResponseEntity<List<EmployeeRankingResponse>> response = traceabilityRestController.getRankingForOrderByEmployeeId(restaurantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void saveTraceability() {
        TraceabilityRequest request = new TraceabilityRequest();

        ResponseEntity<Void> response = traceabilityRestController.saveTraceability(request);

        verify(traceabilityHandler, times(1)).saveTraceability(request);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}