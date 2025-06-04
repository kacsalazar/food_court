package com.foodcourt.usersmanagment.application.handler;

import com.foodcourt.usersmanagment.application.dto.request.AuthRequestDto;

public interface IAuthHandler {

    void userLogin(AuthRequestDto authRequestDto);
}
