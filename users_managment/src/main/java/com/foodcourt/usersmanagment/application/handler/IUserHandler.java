package com.foodcourt.usersmanagment.application.handler;

import com.foodcourt.usersmanagment.application.dto.request.OwnerRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.UserResponseDto;

public interface IUserHandler {

    void saveUser(OwnerRequestDto ownerRequestDto);
    UserResponseDto getUserById(Long idOwner);
    Boolean verifyUserRol(Long idOwner, String rol);
}
