package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.api.IRestaurantServicePort;
import com.foodcourt.squaremallmanagment.domain.model.restaurant.RestaurantModel;
import com.foodcourt.squaremallmanagment.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.squaremallmanagment.domain.usecase.util.RestaurantValidationUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;

    @Override
    public void saveRestaurant(RestaurantModel restaurantModel) {
        RestaurantValidationUtil.isValidRestaurant(restaurantModel);
        restaurantPersistencePort.saveRestaurant(restaurantModel);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants(Integer page, Integer size) {
        return restaurantPersistencePort.getAllRestaurants(page, size);
    }

    @Override
    public RestaurantModel getRestaurantByIdOwner(Long idOwner) {
        return restaurantPersistencePort.findRestaurantByIdOwner(idOwner);
    }

    @Override
    public RestaurantModel findRestaurantById(Long restaurantId) {
        return restaurantPersistencePort.findRestaurantById(restaurantId);
    }
}
