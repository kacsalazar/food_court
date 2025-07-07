package com.foodcourt.squaremallmanagment.infrastructure.configuration;

import com.foodcourt.squaremallmanagment.domain.spi.*;
import com.foodcourt.squaremallmanagment.domain.usecase.*;
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
    public UserClientUseCase userClientUseCase(IUserRestPort iuserRestPort) {
        return new UserClientUseCase(iuserRestPort);
    }

    @Bean
    public DishUseCase dishUseCase(IDishPersistencePort iDishPersistencePort, IUserRestPort iUserRestPort,
                                   IRestaurantPersistencePort restaurantPersistencePort) {
        return new DishUseCase(iDishPersistencePort, iUserRestPort, restaurantPersistencePort);
    }

    @Bean
    public OrderUseCase orderUseCase(IOrderPersistencePort iOrderPersistencePort, IUserRestPort userClientPort,
                                   ISendNotificationPort iSendNotificationPort,
                                     ITraceabilityPersistencePort iTraceabilityPersistencePort,
                                     IDishPersistencePort iDishPersistencePort,
                                     IEmployeeRestPort iEmployeeRestPort) {
        return new OrderUseCase(iOrderPersistencePort, userClientPort, iSendNotificationPort,
                                iTraceabilityPersistencePort, iDishPersistencePort,
                iEmployeeRestPort);
    }

    @Bean
    public TraceabilityUseCase traceabilityUseCase(ITraceabilityPersistencePort iTraceabilityPersistencePort,
                                                               IOrderPersistencePort iOrderPersistencePort,
                                                               IUserRestPort iUserRestPort,
                                                               IRestaurantPersistencePort iRestaurantPersistencePort,
                                                               IEmployeeRestPort iEmployeeRestPort) {
        return new TraceabilityUseCase(iTraceabilityPersistencePort, iOrderPersistencePort, iUserRestPort,
                                       iRestaurantPersistencePort, iEmployeeRestPort);
    }
}