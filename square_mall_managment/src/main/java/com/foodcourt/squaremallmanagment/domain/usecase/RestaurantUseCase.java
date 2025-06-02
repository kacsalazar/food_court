package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.api.IRestaurantServicePort;
import com.foodcourt.squaremallmanagment.domain.model.RestaurantModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantServicePort restaurantServicePort;

    @Override
    public void saveRestaurant(RestaurantModel restaurantModel) {
        restaurantServicePort.saveRestaurant(restaurantModel);
    }
}
