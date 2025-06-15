package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.squaremallmanagment.domain.model.RestaurantModel;
import com.foodcourt.squaremallmanagment.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.RestaurantEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public RestaurantModel findRestaurantById(Long id) {
        return restaurantMapper.toRestaurantModel(restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant with id " + id + " not found.")));
    }

    @Override
    public List<RestaurantModel> getAllRestaurants(Integer page, Integer size) {
        return restaurantMapper.toRestaurantModelList(
                restaurantRepository.findAllByOrderByIdAsc(page, size));
    }
}
