package com.foodcourt.squaremallmanagment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TraceabilityModel {

    private Long orderId;
    private Long customerId;
    private String emailCustomer;
    private LocalDateTime date;
    private String beforeState;
    private String newState;
    private Long employeeId;
    private String employeeEmail;
}
