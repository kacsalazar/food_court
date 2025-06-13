package com.foodcourt.usersmanagment.infraestructure.input.rest;

import com.foodcourt.usersmanagment.application.dto.request.UserRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.UserResponseDto;
import com.foodcourt.usersmanagment.application.handler.IUserHandler;
import com.foodcourt.usersmanagment.CreatorMocks;
import com.foodcourt.usersmanagment.infrastructure.input.rest.UserRestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private IUserHandler userHandler;

    @InjectMocks
    private UserRestController userRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        // Given
        UserRequestDto userRequestDto = CreatorMocks.createOwnerRequestDto();

        // When
        ResponseEntity<Void> response = userRestController.saveUser(userRequestDto);

        // Then
        verify(userHandler, times(1)).saveUser(userRequestDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testGetUserById() {
        // Given
        Long id = 1L;
        UserResponseDto userResponseDto = new UserResponseDto();

        when(userHandler.getUserById(id)).thenReturn(userResponseDto);

        // When
        ResponseEntity<UserResponseDto> response = userRestController.getUserById(id);

        // Then
        verify(userHandler, times(1)).getUserById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponseDto, response.getBody());
    }

    @Test
    void testVerifyUserRol() {
        // Given
        String id = "1L";
        String rol = "ROLE_ADMIN";

        when(userHandler.verifyUserRol(id, rol)).thenReturn(true);

        // When
        ResponseEntity<Boolean> response = userRestController.verifyUserRol(id, rol);

        // Then
        verify(userHandler, times(1)).verifyUserRol(id, rol);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
    }

}
