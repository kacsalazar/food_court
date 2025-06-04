package com.foodcourt.squaremallmanagment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DishModel {

    private String name;
    private Long idCategory;
    private String description;
    private Double price;
    private Long idRestaurant;
    private String imageUrl;

}
