package com.foodcourt.usersmanagment.application.handler.impl;

import com.foodcourt.usersmanagment.application.dto.request.UserRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.UserResponseDto;
import com.foodcourt.usersmanagment.application.mapper.IUserRequestMapper;
import com.foodcourt.usersmanagment.domain.api.IUserServicePort;
import com.foodcourt.usersmanagment.domain.model.SaveUserModel;
import com.foodcourt.usersmanagment.CreatorMocks;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserHandlerTest {

    @Mock
    private IUserRequestMapper userRequestMapper;

    @Mock
    private IUserServicePort userServicePort;

    @InjectMocks
    private UserHandler userHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        // Given
        UserRequestDto userRequestDto = CreatorMocks.createOwnerRequestDto();
        userRequestDto.setPassword("plainPassword");
        SaveUserModel saveUserModel = CreatorMocks.createOwnerModel();
        saveUserModel.setPassword("plainPassword");

        when(userRequestMapper.toUserToSave(userRequestDto)).thenReturn(saveUserModel);


        // When
        userHandler.saveUser(userRequestDto);

        // Then
        verify(userRequestMapper, times(1)).toUserToSave(userRequestDto);

        verify(userServicePort, times(1)).saveOwner(saveUserModel);
        assertEquals("plainPassword", saveUserModel.getPassword());
    }

    @Test
    void getUserByIdTest() {
        // Given
        Long idOwner = 1L;
        UserModel userModel = CreatorMocks.createUserModel();
        UserResponseDto userResponseDto = CreatorMocks.createUserResponseDto();

        when(userServicePort.findUserById(idOwner)).thenReturn(userModel);
        when(userRequestMapper.toUserResponseDto(userModel)).thenReturn(userResponseDto);

        // When
        UserResponseDto result = userHandler.getUserById(idOwner);

        // Then
        verify(userServicePort, times(1)).findUserById(idOwner);
        verify(userRequestMapper, times(1)).toUserResponseDto(userModel);
        assertEquals(userResponseDto, result);
    }

    @Test
    void verifyUserRolTest() {
        // Given
        String idOwner = "1L";
        String rol = "ROLE_ADMIN";

        when(userServicePort.verifyUserRol(idOwner, rol)).thenReturn(true);

        // When
        Boolean result = userHandler.verifyUserRol(idOwner, rol);

        // Then
        verify(userServicePort, times(1)).verifyUserRol(idOwner, rol);
        assertTrue(result);
    }

    @Test
    void createAccountEmployeeTest() {
        // Given
        UserRequestDto userRequestDto = CreatorMocks.createEmployeeRequestDto();
        SaveUserModel saveUserModel = CreatorMocks.createOwnerModel();

        when(userRequestMapper.toUserToSave(userRequestDto)).thenReturn(saveUserModel);

        // When
        userHandler.createAccountEmployee(userRequestDto);

        // Then
        verify(userRequestMapper, times(1)).toUserToSave(userRequestDto);
        verify(userServicePort, times(1)).createAccountEmployee(saveUserModel);
    }

    @Test
    void getUserByDniTest() {
        // Given
        String dni = "123456";
        UserModel userModel = CreatorMocks.createUserModel();
        UserResponseDto userResponseDto = CreatorMocks.createUserResponseDto();

        when(userServicePort.findUserById(dni)).thenReturn(userModel);
        when(userRequestMapper.toUserResponseDto(userModel)).thenReturn(userResponseDto);

        // When
        UserResponseDto result = userHandler.getUserByDni(dni);

        // Then
        verify(userServicePort, times(1)).findUserById(dni);
        verify(userRequestMapper, times(1)).toUserResponseDto(userModel);
        assertEquals(userResponseDto, result);
    }
}
