package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.impl;

import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.ListDishesByRestaurantModel;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.DishEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

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
                .build();
    }

    public static List<ListDishesByRestaurantModel> toDishesByRestaurantModelList (List<DishEntity> dishEntities) {
        return dishEntities.stream()
                .map(dishEntity -> ListDishesByRestaurantModel.builder()
                        .name(dishEntity.getName())
                        .description(dishEntity.getDescription())
                        .imageUrl(dishEntity.getImageUrl())
                        .build())
                .toList();
    }
}
