package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.api.IRestaurantServicePort;
import com.foodcourt.squaremallmanagment.domain.model.RestaurantModel;
import com.foodcourt.squaremallmanagment.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.squaremallmanagment.domain.util.RestaurantValidationUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
