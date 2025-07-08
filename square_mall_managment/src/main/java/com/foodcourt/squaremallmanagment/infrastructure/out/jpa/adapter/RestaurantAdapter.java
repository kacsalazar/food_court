package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.squaremallmanagment.domain.model.restaurant.RestaurantModel;
import com.foodcourt.squaremallmanagment.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.RestaurantEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RestaurantAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantMapper;

    @Override
    public RestaurantModel saveRestaurant(RestaurantModel restaurantModel) {

        RestaurantEntity restaurantEntity = restaurantMapper.toRestaurantEntity(restaurantModel);
        return restaurantMapper.toRestaurantModel( restaurantRepository.save(restaurantEntity));
    }

    @Override
    public RestaurantModel findRestaurantById(Long id) {
        return restaurantMapper.toRestaurantModel(restaurantRepository.findById(id).get());
    }

    @Override
    public List<RestaurantModel> getAllRestaurants(Integer page, Integer size) {
        return restaurantMapper.toRestaurantModelList(
                restaurantRepository.findAllByOrderByIdAsc(page, size));
    }

    @Override
    public RestaurantModel findRestaurantByIdOwner(Long idOwner) {
        return restaurantMapper.toRestaurantModel( restaurantRepository.findRestaurantByIdOwner(idOwner));
    }
}
