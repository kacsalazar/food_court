package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.impl;

import com.foodcourt.squaremallmanagment.domain.model.DishModel;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.DishEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DishEntityMapperData {

    public static DishEntity toDishEntity(DishModel dishModel){

        return DishEntity.builder()
                .name(dishModel.getDishInfo().getName())
                .idCategory(dishModel.getDishInfo().getIdCategory())
                .description(dishModel.getDishInfo().getDescription())
                .price(dishModel.getDishInfo().getPrice())
                .idRestaurant(dishModel.getRestaurantInfo().getIdRestaurant())
                .imageUrl(dishModel.getDishInfo().getImageUrl())
                .isActive(dishModel.getDishInfo().getIsActive())
                .build();
    }

    public static DishModel toDishModel(DishEntity dishEntity){

        return DishModel.builder()
                .dishInfo(DishModel.DishInfo.builder()
                        .name(dishEntity.getName())
                        .idCategory(dishEntity.getIdCategory())
                        .description(dishEntity.getDescription())
                        .price(dishEntity.getPrice())
                        .imageUrl(dishEntity.getImageUrl())
                        .isActive(dishEntity.getIsActive())
                        .build())
                .restaurantInfo(DishModel.RestaurantInfo.builder()
                        .idRestaurant(dishEntity.getIdRestaurant())
                        .build())
                .ownerInfo(DishModel.OwnerInfo.builder()
                        .build())
                .build();
    }
}
