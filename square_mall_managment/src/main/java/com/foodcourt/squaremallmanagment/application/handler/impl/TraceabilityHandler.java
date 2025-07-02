package com.foodcourt.squaremallmanagment.application.handler.impl;

import com.foodcourt.squaremallmanagment.application.dto.response.EmployeeRankingResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.TraceabilityResponse;
import com.foodcourt.squaremallmanagment.application.handler.ITraceabilityHandler;
import com.foodcourt.squaremallmanagment.application.handler.helper.HelperClass;
import com.foodcourt.squaremallmanagment.application.mapper.impl.TraceabilityRequestMapper;
import com.foodcourt.squaremallmanagment.domain.api.ITraceabilityServicePort;
import com.foodcourt.squaremallmanagment.domain.model.EmployeeRankingModel;
import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class TraceabilityHandler implements ITraceabilityHandler {

    private final ITraceabilityServicePort traceabilityServicePort;

    @Override
    public List<TraceabilityResponse> findAllTracesByOrderId(Long orderId) {
        List<TraceabilityModel> traceabilityModels = traceabilityServicePort.findAllTracesByOrderId(orderId, HelperClass.getUserDni());
        return traceabilityModels.stream()
                .map(TraceabilityRequestMapper::toTraceabilityResponse)
                .toList();
    }

    public String getOrderProcessingTime(Long orderId) {
        return traceabilityServicePort.getOrderProcessingTime(orderId, HelperClass.getUserDni());
    }

    public List<EmployeeRankingResponse> getRankingForOrderByEmployeeId(Long orderId) {
        List<EmployeeRankingModel> employeesRankingModel = traceabilityServicePort.getRankingForOrderByEmployeeId(orderId,
                HelperClass.getUserDni());
        return employeesRankingModel.stream()
                .map(TraceabilityRequestMapper::toEmployeeRankingResponse)
                .toList();
    }
}
