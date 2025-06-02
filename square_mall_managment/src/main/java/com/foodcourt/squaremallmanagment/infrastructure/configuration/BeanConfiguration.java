package com.foodcourt.squaremallmanagment.infrastructure.configuration;

import com.foodcourt.squaremallmanagment.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.squaremallmanagment.domain.usecase.RestaurantUseCase;
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
}