package com.foodcourt.usersmanagment.application.handler.impl;

import com.foodcourt.usersmanagment.application.dto.request.AuthRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.TokenResponseDto;
import com.foodcourt.usersmanagment.application.mapper.IAuthRequestMapper;
import com.foodcourt.usersmanagment.domain.api.IAuthServicePort;
import com.foodcourt.usersmanagment.domain.model.AuthModel;
import com.foodcourt.usersmanagment.domain.model.TokenModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthHandlerTest {


    @Mock
    private IAuthRequestMapper authRequestMapper;
    @Mock
    private IAuthServicePort authServicePort;

    @InjectMocks
    private AuthHandler authHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void userLogin() {
        // Arrange
        AuthRequestDto authRequestDto = new AuthRequestDto();
        AuthModel authModel = new AuthModel();
        TokenModel tokenModel = new TokenModel();
        TokenResponseDto tokenResponseDto = new TokenResponseDto();

        when(authRequestMapper.toAuthModel(authRequestDto)).thenReturn(authModel);
        when(authServicePort.userLogin(authModel)).thenReturn(tokenModel);
        when(authRequestMapper.toTokenResponseDto(tokenModel)).thenReturn(tokenResponseDto);

        // Act
        TokenResponseDto result = authHandler.userLogin(authRequestDto);

        // Assert
        assertEquals(tokenResponseDto, result);
        verify(authRequestMapper).toAuthModel(authRequestDto);
        verify(authServicePort).userLogin(authModel);
        verify(authRequestMapper).toTokenResponseDto(tokenModel);
    }

}