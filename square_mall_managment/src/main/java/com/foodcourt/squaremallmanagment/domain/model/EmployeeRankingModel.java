package com.foodcourt.squaremallmanagment.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeRankingModel {

    private Long employeeId;
    private double averageSeconds;
}
