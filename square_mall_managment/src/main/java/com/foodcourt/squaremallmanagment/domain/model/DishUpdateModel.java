package com.foodcourt.squaremallmanagment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DishUpdateModel {
    private Double price;
    private String description;
}
