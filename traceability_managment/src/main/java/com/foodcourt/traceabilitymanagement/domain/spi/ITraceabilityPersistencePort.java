package com.foodcourt.traceabilitymanagement.domain.spi;

import com.foodcourt.traceabilitymanagement.domain.model.TraceabilityModel;

import java.util.List;

public interface ITraceabilityPersistencePort {

    void saveTraceability(TraceabilityModel traceabilityModel);
    List<TraceabilityModel> findAllTracesByOrderId(Long orderId);
    List<TraceabilityModel> findAllByOrderIdAndStatus(Long OrderId);
}
