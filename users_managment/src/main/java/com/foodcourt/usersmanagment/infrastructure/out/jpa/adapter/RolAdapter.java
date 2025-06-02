package com.foodcourt.usersmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.usersmanagment.domain.model.GetRolModel;
import com.foodcourt.usersmanagment.domain.spi.IRolPersistencePort;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.RolEntity;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.mapper.IRolEntityMapper;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.repository.IRolRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class RolAdapter implements IRolPersistencePort {

    private final IRolRepository rolRepository;
    private final IRolEntityMapper rolEntityMapper;

    @Override
    public GetRolModel findByName(String name) {

        RolEntity rolEntity = rolRepository.findByName(name);
        return rolEntityMapper.toGetRolModel(rolEntity);
    }
}
