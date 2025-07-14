package com.foodcourt.squaremallmanagment.domain.spi;

import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;

public interface ITraceabilityRestPort {

    void saveTraceability(TraceabilityModel traceabilityModel);
}
