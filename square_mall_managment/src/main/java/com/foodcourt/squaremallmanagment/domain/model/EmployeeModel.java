package com.foodcourt.squaremallmanagment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeModel {

    private String name;
    private String dni;
    private Long id;
    private String phoneNumber;
    private String email;
    private Long employeeRestaurantId;
}
