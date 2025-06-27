package com.foodcourt.squaremallmanagment.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DishCreateRequest {

    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Long idCategory;
    private Long idRestaurant;
}
