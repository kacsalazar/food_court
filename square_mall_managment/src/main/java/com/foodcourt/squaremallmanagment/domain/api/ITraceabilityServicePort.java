package com.foodcourt.squaremallmanagment.domain.api;

import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;

import java.util.List;

public interface ITraceabilityServicePort {

    List<TraceabilityModel> findAllTracesByOrderId(Long orderId, String userDni);
}
