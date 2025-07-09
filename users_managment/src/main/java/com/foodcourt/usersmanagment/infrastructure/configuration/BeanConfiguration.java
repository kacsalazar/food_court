package com.foodcourt.usersmanagment.infrastructure.configuration;

import com.foodcourt.usersmanagment.domain.spi.IAuthPort;
import com.foodcourt.usersmanagment.domain.spi.IRestaurantClientPort;
import com.foodcourt.usersmanagment.domain.spi.IRolPersistencePort;
import com.foodcourt.usersmanagment.domain.spi.IUserPersistencePort;
import com.foodcourt.usersmanagment.domain.usecase.AuthUseCase;
import com.foodcourt.usersmanagment.domain.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    @Bean
    public UserUseCase userServicePort(IUserPersistencePort iUserPersistencePort,
                                       IRolPersistencePort iRolPersistencePort
    ) {
        return new UserUseCase( iUserPersistencePort, iRolPersistencePort );
    }

    @Bean
    public AuthUseCase authUseCase(IAuthPort iAuthPort, IRolPersistencePort iRolPort,
                                   IUserPersistencePort iUserPersistencePort){
        return new AuthUseCase(iAuthPort, iRolPort, iUserPersistencePort);
    }
}