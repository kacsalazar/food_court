package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "traceability")
@Builder
public class TraceabilityEntity {

    @Id
    private String id;
    private String orderId;
    private String customerId;
    private String emailCustomer;
    private LocalDateTime date;
    private String beforeState;
    private String newState;
    private String employeeId;
    private String employeeEmail;
}
