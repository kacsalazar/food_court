package com.foodcourt.squaremallmanagment.infrastructure.configuration;

import com.foodcourt.squaremallmanagment.domain.spi.IDishPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IUserClientPort;
import com.foodcourt.squaremallmanagment.domain.usecase.DishUseCase;
import com.foodcourt.squaremallmanagment.domain.usecase.RestaurantUseCase;
import com.foodcourt.squaremallmanagment.domain.usecase.UserClientUseCase;
import com.foodcourt.squaremallmanagment.domain.usecase.util.DishValidationUtil;
import com.foodcourt.squaremallmanagment.domain.usecase.util.RestaurantValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    @Bean
    public RestaurantUseCase restaurantServicePort(IRestaurantPersistencePort irestaurantPersistencePort) {
        return new RestaurantUseCase(irestaurantPersistencePort);
    }

    @Bean
    public UserClientUseCase userClientUseCase(IUserClientPort iuserClientPort) {
        return new UserClientUseCase(iuserClientPort);
    }

    @Bean
    public DishUseCase dishUseCase(IDishPersistencePort iDishPersistencePort) {
        return new DishUseCase(iDishPersistencePort);
    }
}