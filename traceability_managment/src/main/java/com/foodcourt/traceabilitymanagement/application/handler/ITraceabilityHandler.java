package com.foodcourt.traceabilitymanagement.application.handler;

import com.foodcourt.traceabilitymanagement.application.dto.request.TraceabilityRequest;
import com.foodcourt.traceabilitymanagement.application.dto.response.DurationTimeResponse;
import com.foodcourt.traceabilitymanagement.application.dto.response.EmployeeRankingResponse;
import com.foodcourt.traceabilitymanagement.application.dto.response.TraceabilityResponse;

import java.util.List;

public interface ITraceabilityHandler {
    List<TraceabilityResponse> findAllTracesByOrderId(Long orderId);
    List<DurationTimeResponse> getOrdersProcessingTime(Long orderId);
    List<EmployeeRankingResponse> getRankingForOrderByEmployeeId(Long restaurantId);
    void saveTraceability(TraceabilityRequest traceabilityRequest);
}
