package com.foodcourt.squaremallmanagment.application.dto.response;

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

    private String orderId;
    private String customerId;
    private String emailCustomer;
    private LocalDateTime date;
    private String beforeState;
    private String newState;
    private String employeeId;
    private String employeeEmail;
}
