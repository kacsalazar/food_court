package com.foodcourt.usersmanagment.application.handler.impl;

import com.foodcourt.usersmanagment.application.dto.request.AuthRequestDto;
import com.foodcourt.usersmanagment.application.handler.IAuthHandler;
import com.foodcourt.usersmanagment.application.mapper.IAuthRequestMapper;
import com.foodcourt.usersmanagment.domain.api.IAuthServicePort;
import com.foodcourt.usersmanagment.domain.model.AuthModel;
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
    public void userLogin(AuthRequestDto authRequestDto) {
        AuthModel authModel = authRequestMapper.toAuthModel(authRequestDto);
        authServicePort.userLogin(authModel);
    }
}
