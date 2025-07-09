package com.foodcourt.squaremallmanagment.domain.model.dish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ListDishesRetrieved {

    private Long restaurantId;
    private Long categoryId;
    private Integer offset;
    private Integer size;
}
