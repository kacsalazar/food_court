package com.foodcourt.squaremallmanagment.domain.spi;

import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;

public interface ITraceabilityPersistencePort {

    void saveTraceability(TraceabilityModel traceabilityModel);

}
