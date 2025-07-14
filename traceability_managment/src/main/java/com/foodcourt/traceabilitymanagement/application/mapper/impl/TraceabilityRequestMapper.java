package com.foodcourt.traceabilitymanagement.application.mapper.impl;

import com.foodcourt.traceabilitymanagement.application.dto.request.TraceabilityRequest;
import com.foodcourt.traceabilitymanagement.application.dto.response.DurationTimeResponse;
import com.foodcourt.traceabilitymanagement.application.dto.response.EmployeeRankingResponse;
import com.foodcourt.traceabilitymanagement.application.dto.response.TraceabilityResponse;
import com.foodcourt.traceabilitymanagement.domain.model.EmployeeRankingModel;
import com.foodcourt.traceabilitymanagement.domain.model.TraceabilityModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TraceabilityRequestMapper {
    public static TraceabilityResponse toTraceabilityResponse(TraceabilityModel traceabilityModel) {

        return TraceabilityResponse.builder()
                .orderId(traceabilityModel.getOrderId())
                .customerId(traceabilityModel.getCustomerId())
                .emailCustomer(traceabilityModel.getEmailCustomer())
                .date(traceabilityModel.getDate())
                .beforeState(traceabilityModel.getBeforeState())
                .newState(traceabilityModel.getNewState())
                .employeeId(traceabilityModel.getEmployeeId())
                .employeeEmail(traceabilityModel.getEmployeeEmail())
                .build();

    }

    public static EmployeeRankingResponse toEmployeeRankingResponse(EmployeeRankingModel employeeRankingModel) {

        return EmployeeRankingResponse.builder()
                .employeeId(employeeRankingModel.getEmployeeId())
                .averageSeconds(employeeRankingModel.getAverageSeconds())
                .build();
    }

    public static TraceabilityModel toTraceabilityModel(TraceabilityRequest traceabilityRequest) {
        return TraceabilityModel.builder()
                .orderId(traceabilityRequest.getOrderId())
                .customerId(traceabilityRequest.getCustomerId())
                .emailCustomer(traceabilityRequest.getEmailCustomer())
                .date(traceabilityRequest.getDate())
                .beforeState(traceabilityRequest.getBeforeState())
                .newState(traceabilityRequest.getNewState())
                .employeeId(traceabilityRequest.getEmployeeId())
                .employeeEmail(traceabilityRequest.getEmployeeEmail())
                .build();
    }

    public static DurationTimeResponse toDurationTimeResponse(String duration) {
        return DurationTimeResponse.builder()
                .durationTime(duration)
                .build();
    }

}
