package com.foodcourt.squaremallmanagment.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DishResponseDto {

    private String name;
    private Long idCategory;
    private String description;
    private Double price;
    private Long idRestaurant;
    private String imageUrl;
}
