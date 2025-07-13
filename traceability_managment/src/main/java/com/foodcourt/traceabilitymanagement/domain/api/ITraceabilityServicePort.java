package com.foodcourt.traceabilitymanagement.domain.api;

import com.foodcourt.traceabilitymanagement.domain.model.EmployeeRankingModel;
import com.foodcourt.traceabilitymanagement.domain.model.TraceabilityModel;

import java.util.List;

public interface ITraceabilityServicePort {
    List<TraceabilityModel> findAllTracesByOrderId(Long orderId, String userDni);
    List<String> getOrdersProcessingTime(Long restaurantId, String userDni);
    List<EmployeeRankingModel> getRankingForOrderByEmployeeId(Long restaurantId, String ownerDni);
    void saveTraceability (TraceabilityModel traceabilityModel);

}
