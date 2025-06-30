package com.foodcourt.usersmanagment.application.handler.impl;

import com.foodcourt.usersmanagment.application.dto.request.OwnerRequestDto;
import com.foodcourt.usersmanagment.application.dto.request.UserRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.UserResponseDto;
import com.foodcourt.usersmanagment.application.handler.IUserHandler;
import com.foodcourt.usersmanagment.application.handler.util.UtilClass;
import com.foodcourt.usersmanagment.application.mapper.IUserRequestMapper;
import com.foodcourt.usersmanagment.application.mapper.impl.UserRequestMapper;
import com.foodcourt.usersmanagment.domain.api.IUserServicePort;
import com.foodcourt.usersmanagment.domain.model.CreateUserModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserRequestMapper userRequestMapper;
    private final IUserServicePort userServicePort;

    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        CreateUserModel createUserModel = userRequestMapper.toUserToSave(userRequestDto);
        userServicePort.saveOwner(createUserModel);
    }

    @Override
    public UserResponseDto getUserById(Long idOwner) {
        UserModel userModel = userServicePort.findUserById(idOwner);
        return userRequestMapper.toUserResponseDto(userModel);
    }

    @Override
    public Boolean verifyUserRol(String dni, String rol) {
        return userServicePort.verifyUserRol(dni, rol);
    }

    @Override
    public void createAccountEmployee(OwnerRequestDto userRequestDto) {
        CreateUserModel createUserModel = userRequestMapper.toOwnerModel(userRequestDto);
        userServicePort.createAccountEmployee(createUserModel, UtilClass.getUserDni());
    }

    @Override
    public UserResponseDto getUserByDni(String dni) {
        UserModel userModel = userServicePort.getUserByDni(dni);
        return UserRequestMapper.toUserResponseDto(userModel);
    }

    @Override
    public void createAccountCustomer(UserRequestDto userRequestDto) {
        CreateUserModel createUserModel = userRequestMapper.toUserToSave(userRequestDto);
        userServicePort.createAccountCustomer(createUserModel);
    }

}
