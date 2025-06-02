package com.foodcourt.usersmanagment.infrastructure.configuration;

import com.foodcourt.usersmanagment.domain.spi.IUserPersistencePort;
import com.foodcourt.usersmanagment.domain.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    @Bean
    public UserUseCase userServicePort(IUserPersistencePort iUserPersistencePort) {
        return new UserUseCase(iUserPersistencePort);
    }
}