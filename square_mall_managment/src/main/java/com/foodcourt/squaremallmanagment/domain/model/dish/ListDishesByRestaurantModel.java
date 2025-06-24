package com.foodcourt.squaremallmanagment.domain.model.dish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ListDishesByRestaurantModel {

    private String name;
    private String description;
    private String imageUrl;
}
