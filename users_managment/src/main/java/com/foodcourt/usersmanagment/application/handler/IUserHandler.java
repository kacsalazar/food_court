package com.foodcourt.usersmanagment.application.handler;

import com.foodcourt.usersmanagment.application.dto.request.UserRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.UserResponseDto;

public interface IUserHandler {

    void saveUser(UserRequestDto userRequestDto);
    UserResponseDto getUserById(Long idOwner);
    Boolean verifyUserRol(String dni, String rol);
    void createAccountEmployee(UserRequestDto userRequestDto);
    UserResponseDto getUserByDni(String dni);
    void createAccountCustomer(UserRequestDto userRequestDto);
}
