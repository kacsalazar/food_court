package com.foodcourt.squaremallmanagment.application.mapper.impl;

import com.foodcourt.squaremallmanagment.application.dto.response.EmployeeRankingResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.TraceabilityResponse;
import com.foodcourt.squaremallmanagment.domain.model.EmployeeRankingModel;
import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;
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

}
