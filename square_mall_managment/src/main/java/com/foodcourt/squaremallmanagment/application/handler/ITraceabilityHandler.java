package com.foodcourt.squaremallmanagment.application.handler;

import com.foodcourt.squaremallmanagment.application.dto.response.TraceabilityResponse;

import java.util.List;

public interface ITraceabilityHandler {

    List<TraceabilityResponse> findAllTracesByOrderId(Long orderId);
    String getOrderProcessingTime(Long orderId);
}
