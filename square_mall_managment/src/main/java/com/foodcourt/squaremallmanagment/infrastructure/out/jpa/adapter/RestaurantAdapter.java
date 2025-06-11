package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.squaremallmanagment.domain.model.RestaurantModel;
import com.foodcourt.squaremallmanagment.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.RestaurantEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class RestaurantAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantMapper;

    @Override
    public RestaurantModel saveRestaurant(RestaurantModel restaurantModel) {

        RestaurantEntity restaurantEntity = restaurantMapper.toRestaurantEntity(restaurantModel);
        RestaurantEntity r  = restaurantRepository.save(restaurantEntity);
        return restaurantMapper.toRestaurantModel(restaurantEntity);
    }
}
