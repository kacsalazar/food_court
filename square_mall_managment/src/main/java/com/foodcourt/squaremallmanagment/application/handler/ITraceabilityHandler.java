package com.foodcourt.squaremallmanagment.application.handler;

import com.foodcourt.squaremallmanagment.application.dto.response.EmployeeRankingResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.TraceabilityResponse;
import com.foodcourt.squaremallmanagment.domain.model.EmployeeRankingModel;

import java.util.List;

public interface ITraceabilityHandler {

    List<TraceabilityResponse> findAllTracesByOrderId(Long orderId);
    String getOrderProcessingTime(Long orderId);
    List<EmployeeRankingResponse> getRankingForOrderByEmployeeId(Long orderId);
}
