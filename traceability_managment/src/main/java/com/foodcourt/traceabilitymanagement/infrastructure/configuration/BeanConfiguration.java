package com.foodcourt.traceabilitymanagement.infrastructure.configuration;

import com.foodcourt.traceabilitymanagement.domain.spi.*;
import com.foodcourt.traceabilitymanagement.domain.usecase.TraceabilityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    @Bean
    public TraceabilityUseCase traceabilityUseCase(ITraceabilityPersistencePort traceabilityPersistencePort, IOrderRestPort orderRestPort,
                                                   IUserRestPort userRestPort, IRestaurantRestPort restaurantRestPort, IEmployeeRestPort employeeRestPort){
        return new TraceabilityUseCase(traceabilityPersistencePort, orderRestPort, userRestPort, restaurantRestPort, employeeRestPort);
    }
}