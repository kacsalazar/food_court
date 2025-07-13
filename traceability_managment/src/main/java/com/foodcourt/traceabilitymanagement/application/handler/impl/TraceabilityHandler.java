package com.foodcourt.traceabilitymanagement.application.handler.impl;

import com.foodcourt.traceabilitymanagement.application.dto.request.TraceabilityRequest;
import com.foodcourt.traceabilitymanagement.application.dto.response.DurationTimeResponse;
import com.foodcourt.traceabilitymanagement.application.dto.response.EmployeeRankingResponse;
import com.foodcourt.traceabilitymanagement.application.dto.response.TraceabilityResponse;
import com.foodcourt.traceabilitymanagement.application.handler.ITraceabilityHandler;
import com.foodcourt.traceabilitymanagement.application.handler.helper.HelperClass;
import com.foodcourt.traceabilitymanagement.application.mapper.impl.TraceabilityRequestMapper;
import com.foodcourt.traceabilitymanagement.domain.api.ITraceabilityServicePort;
import com.foodcourt.traceabilitymanagement.domain.model.EmployeeRankingModel;
import com.foodcourt.traceabilitymanagement.domain.model.TraceabilityModel;
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
    private final HelperClass helperClass;

    @Override
    public List<TraceabilityResponse> findAllTracesByOrderId(Long orderId) {
        List<TraceabilityModel> traceabilityModels = traceabilityServicePort.findAllTracesByOrderId(orderId,  helperClass.getUserDni());
        return traceabilityModels.stream()
                .map(TraceabilityRequestMapper::toTraceabilityResponse)
                .toList();
    }


    public List<DurationTimeResponse> getOrdersProcessingTime(Long restaurantId) {
        List<String> durations = traceabilityServicePort.getOrdersProcessingTime(restaurantId,  helperClass.getUserDni());
        return durations.stream()
                .map(TraceabilityRequestMapper::toDurationTimeResponse)
                .toList();
    }

    public List<EmployeeRankingResponse> getRankingForOrderByEmployeeId(Long restaurantId) {
        List<EmployeeRankingModel> employeesRankingModel = traceabilityServicePort.getRankingForOrderByEmployeeId(restaurantId,
                helperClass.getUserDni());
        return employeesRankingModel.stream()
                .map(TraceabilityRequestMapper::toEmployeeRankingResponse)
                .toList();
    }

    @Override
    public void saveTraceability(TraceabilityRequest traceabilityRequest) {
        traceabilityServicePort.saveTraceability(TraceabilityRequestMapper.toTraceabilityModel(traceabilityRequest));
    }
}
