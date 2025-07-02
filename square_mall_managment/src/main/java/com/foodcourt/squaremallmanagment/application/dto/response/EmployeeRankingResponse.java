package com.foodcourt.squaremallmanagment.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeRankingResponse {

    private Long employeeId;
    private double averageSeconds;
}
