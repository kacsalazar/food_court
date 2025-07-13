package com.foodcourt.traceabilitymanagement.infrastructure.out.jpa.mapper;


import com.foodcourt.traceabilitymanagement.domain.model.TraceabilityModel;
import com.foodcourt.traceabilitymanagement.infrastructure.out.jpa.entity.TraceabilityEntity;
import lombok.experimental.UtilityClass;

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

}
