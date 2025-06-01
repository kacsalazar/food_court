package com.foodcourt.usersmanagment.application.handler.impl;

import com.foodcourt.usersmanagment.application.dto.request.OwnerRequestDto;
import com.foodcourt.usersmanagment.application.handler.IUserHandler;
import com.foodcourt.usersmanagment.application.mapper.IUserRequestMapper;
import com.foodcourt.usersmanagment.domain.api.IUserServicePort;
import com.foodcourt.usersmanagment.domain.model.OwnerModel;
import com.foodcourt.usersmanagment.infrastructure.configuration.PasswordEncoderConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserRequestMapper userRequestMapper;
    private final IUserServicePort userServicePort;
    private final PasswordEncoderConfig passwordEncoderConfig;

    @Override
    public void saveUser(OwnerRequestDto ownerRequestDto) {

        OwnerModel ownerModel = userRequestMapper.toOwner(ownerRequestDto);
        ownerModel.setPassword(passwordEncoderConfig.passwordEncoder().encode(ownerModel.getPassword()));
        log.info("User Handler: {}" + ownerModel);
        userServicePort.saveOwner(ownerModel);
    }
}
