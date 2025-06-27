package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.impl;

import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.TraceabilityEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class TraceabilityEntityMapper {

    public static TraceabilityEntity toTraceabilityEntity(TraceabilityModel traceabilityModel) {

        return TraceabilityEntity.builder()

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

    public static TraceabilityModel toTraceabilityModel(TraceabilityEntity traceabilityEntity) {

        return TraceabilityModel.builder()
                .orderId(traceabilityEntity.getOrderId())
                .customerId(traceabilityEntity.getCustomerId())
                .emailCustomer(traceabilityEntity.getEmailCustomer())
                .date(traceabilityEntity.getDate())
                .beforeState(traceabilityEntity.getBeforeState())
                .newState(traceabilityEntity.getNewState())
                .employeeId(traceabilityEntity.getEmployeeId())
                .employeeEmail(traceabilityEntity.getEmployeeEmail())
                .build();
    }

    public static List<TraceabilityModel> toTraceabilityModelList(List<TraceabilityEntity> traceabilityEntities) {
        return traceabilityEntities.stream()
                .map(TraceabilityEntityMapper::toTraceabilityModel)
                .toList();
    }

}
