package com.foodcourt.usersmanagment.infrastructure.configuration;

import com.foodcourt.usersmanagment.domain.spi.IAuthPort;
import com.foodcourt.usersmanagment.domain.spi.IUserPersistencePort;
import com.foodcourt.usersmanagment.domain.usecase.AuthUseCase;
import com.foodcourt.usersmanagment.domain.usecase.UserUseCase;
import com.foodcourt.usersmanagment.domain.util.UseValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    @Bean
    public UserUseCase userServicePort(IUserPersistencePort iUserPersistencePort,
                                        UseValidationUtil useValidationUtil) {
        return new UserUseCase(useValidationUtil, iUserPersistencePort);
    }

    @Bean
    public AuthUseCase authUseCase(IAuthPort iAuthPort){
        return new AuthUseCase(iAuthPort);
    }
}