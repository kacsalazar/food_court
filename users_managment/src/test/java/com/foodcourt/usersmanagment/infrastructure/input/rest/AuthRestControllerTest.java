package com.foodcourt.usersmanagment.infrastructure.input.rest;

import com.foodcourt.usersmanagment.application.dto.request.AuthRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.TokenResponseDto;
import com.foodcourt.usersmanagment.application.handler.IAuthHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthRestControllerTest {


    @Mock
    private IAuthHandler authHandler;

    @InjectMocks
    private AuthRestController authRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void userLogin_ReturnsTokenResponseDto() {
        // Arrange
        AuthRequestDto authRequestDto = new AuthRequestDto();
        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        when(authHandler.userLogin(authRequestDto)).thenReturn(tokenResponseDto);

        // Act
        ResponseEntity<TokenResponseDto> response = authRestController.userLogin(authRequestDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tokenResponseDto, response.getBody());
        verify(authHandler, times(1)).userLogin(authRequestDto);
    }

}