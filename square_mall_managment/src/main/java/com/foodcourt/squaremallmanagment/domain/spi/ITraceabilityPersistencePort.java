package com.foodcourt.squaremallmanagment.domain.spi;

import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;

import java.util.List;

public interface ITraceabilityPersistencePort {

    void saveTraceability(TraceabilityModel traceabilityModel);
    List<TraceabilityModel> findAllTracesByOrderId(Long orderId);
}
