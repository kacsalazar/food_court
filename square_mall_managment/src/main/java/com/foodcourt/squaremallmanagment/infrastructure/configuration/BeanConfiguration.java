package com.foodcourt.squaremallmanagment.infrastructure.configuration;

import com.foodcourt.squaremallmanagment.domain.spi.*;
import com.foodcourt.squaremallmanagment.domain.usecase.DishUseCase;
import com.foodcourt.squaremallmanagment.domain.usecase.OrderUseCase;
import com.foodcourt.squaremallmanagment.domain.usecase.RestaurantUseCase;
import com.foodcourt.squaremallmanagment.domain.usecase.UserClientUseCase;
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
    public DishUseCase dishUseCase(IDishPersistencePort iDishPersistencePort, IUserClientPort iUserClientPort,
                                   IRestaurantPersistencePort restaurantPersistencePort) {
        return new DishUseCase(iDishPersistencePort, iUserClientPort, restaurantPersistencePort);
    }

    @Bean
    public OrderUseCase orderUseCase(IOrderPersistencePort iOrderPersistencePort, IUserClientPort userClientPort,
                                   ISendNotificationPort iSendNotificationPort,
                                     ITraceabilityPersistencePort iTraceabilityPersistencePort,
                                     IDishPersistencePort iDishPersistencePort) {
        return new OrderUseCase(iOrderPersistencePort, userClientPort, iSendNotificationPort,
                                iTraceabilityPersistencePort, iDishPersistencePort);
    }
}