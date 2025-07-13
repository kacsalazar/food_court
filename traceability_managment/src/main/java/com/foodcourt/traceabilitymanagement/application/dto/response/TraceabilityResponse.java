package com.foodcourt.traceabilitymanagement.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TraceabilityResponse {

    private Long orderId;
    private Long customerId;
    private String emailCustomer;
    private LocalDateTime date;
    private String beforeState;
    private String newState;
    private Long employeeId;
    private String employeeEmail;
}
