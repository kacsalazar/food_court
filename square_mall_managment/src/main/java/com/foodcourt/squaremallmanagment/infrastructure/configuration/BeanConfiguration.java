package com.foodcourt.squaremallmanagment.infrastructure.configuration;

import com.foodcourt.squaremallmanagment.domain.spi.IDishPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IUserClientPort;
import com.foodcourt.squaremallmanagment.domain.usecase.DishUseCase;
import com.foodcourt.squaremallmanagment.domain.usecase.RestaurantUseCase;
import com.foodcourt.squaremallmanagment.domain.usecase.UserClientUseCase;
import com.foodcourt.squaremallmanagment.domain.util.DishValidationUtil;
import com.foodcourt.squaremallmanagment.domain.util.RestaurantValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    @Bean
    public RestaurantUseCase restaurantServicePort(IRestaurantPersistencePort irestaurantPersistencePort,
                                                   RestaurantValidationUtil restaurantValidationUtil) {
        return new RestaurantUseCase(irestaurantPersistencePort, restaurantValidationUtil);
    }

    @Bean
    public UserClientUseCase userClientUseCase(IUserClientPort iuserClientPort) {
        return new UserClientUseCase(iuserClientPort);
    }

    @Bean
    public DishUseCase dishUseCase(IDishPersistencePort iDishPersistencePort, DishValidationUtil dishValidationUtil) {
        return new DishUseCase(iDishPersistencePort, dishValidationUtil);
    }
}