package com.foodcourt.squaremallmanagment.domain.model.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderModelReturn {

    private Long restaurantId;
    private Long employeeId;
    private String userDni;
    private String status;
    private LocalDate orderDate;

}
