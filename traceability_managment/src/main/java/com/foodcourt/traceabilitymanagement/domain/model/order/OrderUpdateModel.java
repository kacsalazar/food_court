package com.foodcourt.traceabilitymanagement.domain.model.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderUpdateModel {

    private Long id;
    private Long idClient;
    private Date orderDate;
    private String status;
    private Long idChef;
    private Long idRestaurant;
    private String securityPin;
}
