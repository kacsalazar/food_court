package com.foodcourt.squaremallmanagment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderModelReturn {

    private Long restaurantId;
    private Long employeeId;
    //private List<Dish> dishes;
    private String userDni;
    private String status;
    private LocalDate orderDate;

}
