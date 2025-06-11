package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.api.IRestaurantServicePort;
import com.foodcourt.squaremallmanagment.domain.model.RestaurantModel;
import com.foodcourt.squaremallmanagment.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.squaremallmanagment.domain.usecase.util.RestaurantValidationUtil;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final RestaurantValidationUtil restaurantValidationUtil;

    @Override
    public void saveRestaurant(RestaurantModel restaurantModel) {
        restaurantValidationUtil.isValidRestaurant(restaurantModel);
        restaurantPersistencePort.saveRestaurant(restaurantModel);
    }
}
