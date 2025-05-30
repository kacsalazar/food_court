package com.foodcourt.usersmanagment.infrastructure.configuration;

import com.foodcourt.usersmanagment.domain.api.IObjectServicePort;
import com.foodcourt.usersmanagment.domain.spi.IObjectPersistencePort;
import com.foodcourt.usersmanagment.domain.usecase.ObjectUseCase;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.adapter.ObjectJpaAdapter;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.mapper.IObjectEntityMapper;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.repository.IObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    //private final IObjectRepository objectRepository;
    //private final IObjectEntityMapper objectEntityMapper;

    @Bean
    public IObjectPersistencePort objectPersistencePort() {
        //return new ObjectJpaAdapter(objectRepository, objectEntityMapper);
        return null;
    }

    @Bean
    public IObjectServicePort objectServicePort() {
        return new ObjectUseCase(objectPersistencePort());
    }
}