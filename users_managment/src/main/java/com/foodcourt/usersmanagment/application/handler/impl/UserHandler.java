package com.foodcourt.usersmanagment.application.handler.impl;

import com.foodcourt.usersmanagment.application.dto.request.OwnerRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.UserResponseDto;
import com.foodcourt.usersmanagment.application.handler.IUserHandler;
import com.foodcourt.usersmanagment.application.mapper.IUserRequestMapper;
import com.foodcourt.usersmanagment.domain.api.IUserServicePort;
import com.foodcourt.usersmanagment.domain.model.OwnerModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserRequestMapper userRequestMapper;
    private final IUserServicePort userServicePort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(OwnerRequestDto ownerRequestDto) {
        OwnerModel ownerModel = userRequestMapper.toOwner(ownerRequestDto);
        ownerModel.setPassword(passwordEncoder.encode(ownerModel.getPassword()));
        log.info("User Handler: {}" + ownerModel);
        userServicePort.saveOwner(ownerModel);
    }

    @Override
    public UserResponseDto getUserById(Long idOwner) {
        UserModel userModel = userServicePort.findUserById(idOwner);
        return userRequestMapper.toUserResponseDto(userModel);
    }

    @Override
    public Boolean verifyUserRol(Long idOwner, String rol) {
        return userServicePort.verifyUserRol(idOwner, rol);
    }
}
