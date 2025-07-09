package com.foodcourt.squaremallmanagment.application.mapper.impl;

import com.foodcourt.squaremallmanagment.application.dto.request.DishCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponse;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.ListDishesRetrieved;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DishRequestMapperModel {

    public static DishModel toDishModel(DishCreateRequest dishCreateRequest) {
        return DishModel.builder()
                .dishInfo(DishModel.DishInfo.builder()
                        .name(dishCreateRequest.getName())
                        .description(dishCreateRequest.getDescription())
                        .price(dishCreateRequest.getPrice())
                        .imageUrl(dishCreateRequest.getImageUrl())
                        .idCategory(dishCreateRequest.getIdCategory())
                        .build())
                .restaurantInfo(DishModel.RestaurantInfo.builder()
                        .idRestaurant(dishCreateRequest.getIdRestaurant())
                        .build())
                .build();
    }

    public static DishResponse toDishResponseDto(DishModel dishModel) {
        return DishResponse.builder()
                .name(dishModel.getDishInfo().getName())
                .description(dishModel.getDishInfo().getDescription())
                .price(dishModel.getDishInfo().getPrice())
                .imageUrl(dishModel.getDishInfo().getImageUrl())
                .idCategory(dishModel.getDishInfo().getIdCategory())
                .idRestaurant(dishModel.getRestaurantInfo().getIdRestaurant())
                .build();
    }

    public static ListDishesRetrieved toListDishesRetrieve(Long restaurantId, Long categoryId, Integer offset, Integer size){
        return ListDishesRetrieved.builder()
                .restaurantId(restaurantId)
                .categoryId(categoryId)
                .offset(offset)
                .size(size)
                .build();
    }
}
