package com.foodcourt.usersmanagment.application.handler;

import com.foodcourt.usersmanagment.application.dto.request.OwnerRequestDto;

public interface IUserHandler {

    void saveUser(OwnerRequestDto ownerRequestDto);
}
