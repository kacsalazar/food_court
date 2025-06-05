package com.foodcourt.usersmanagment.application.handler.impl;

import com.foodcourt.usersmanagment.application.dto.request.AuthRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.TokenResponseDto;
import com.foodcourt.usersmanagment.application.handler.IAuthHandler;
import com.foodcourt.usersmanagment.application.mapper.IAuthRequestMapper;
import com.foodcourt.usersmanagment.domain.api.IAuthServicePort;
import com.foodcourt.usersmanagment.domain.model.AuthModel;
import com.foodcourt.usersmanagment.domain.model.ClaimUserModel;
import com.foodcourt.usersmanagment.domain.model.TokenModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class AuthHandler implements IAuthHandler {

    private final IAuthRequestMapper authRequestMapper;
    private final IAuthServicePort authServicePort;

    @Override
    public TokenResponseDto userLogin(AuthRequestDto authRequestDto) {
        return authRequestMapper.toTokenResponseDto(authServicePort.userLogin(
                authRequestMapper.toAuthModel(authRequestDto)
        ));
    }
}
