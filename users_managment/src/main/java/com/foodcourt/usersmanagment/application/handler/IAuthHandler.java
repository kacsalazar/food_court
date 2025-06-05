package com.foodcourt.usersmanagment.application.handler;

import com.foodcourt.usersmanagment.application.dto.request.AuthRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.TokenResponseDto;

public interface IAuthHandler {

    TokenResponseDto userLogin(AuthRequestDto authRequestDto);
}
