package com.foodcourt.squaremallmanagment.infrastructure.input.rest;

import com.foodcourt.squaremallmanagment.application.dto.response.EmployeeRankingResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.TraceabilityResponse;
import com.foodcourt.squaremallmanagment.application.handler.ITraceabilityHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    void getTraceabilityByOrderId_shouldReturnListOfTraceabilityResponses() {
        // Arrange
        Long orderId = 1L;
        List<TraceabilityResponse> expectedList = List.of(new TraceabilityResponse(), new TraceabilityResponse());
        when(traceabilityHandler.findAllTracesByOrderId(orderId)).thenReturn(expectedList);

        // Act
        ResponseEntity<List<TraceabilityResponse>> response = traceabilityRestController.getTraceabilityByOrderId(orderId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedList, response.getBody());
        verify(traceabilityHandler).findAllTracesByOrderId(orderId);
    }

    @Test
    void getOrderProcessingTime_shouldReturnProcessingTimeString() {
        // Arrange
        Long orderId = 2L;
        String expectedTime = "25 minutes";
        when(traceabilityHandler.getOrderProcessingTime(orderId)).thenReturn(expectedTime);

        // Act
        ResponseEntity<String> response = traceabilityRestController.getOrderProcessingTime(orderId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedTime, response.getBody());
        verify(traceabilityHandler).getOrderProcessingTime(orderId);
    }

    @Test
    void getRankingForOrderByEmployeeId_shouldReturnListOfEmployeeRankingResponses() {
        // Arrange
        Long restaurantId = 3L;
        List<EmployeeRankingResponse> expectedRanking = List.of(new EmployeeRankingResponse(), new EmployeeRankingResponse());
        when(traceabilityHandler.getRankingForOrderByEmployeeId(restaurantId)).thenReturn(expectedRanking);

        // Act
        ResponseEntity<List<EmployeeRankingResponse>> response = traceabilityRestController.getRankingForOrderByEmployeeId(restaurantId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedRanking, response.getBody());
        verify(traceabilityHandler).getRankingForOrderByEmployeeId(restaurantId);
    }

}