package com.foodcourt.usersmanagment.application.handler.impl;

import com.foodcourt.usersmanagment.application.dto.request.OwnerRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.UserResponseDto;
import com.foodcourt.usersmanagment.application.mapper.IUserRequestMapper;
import com.foodcourt.usersmanagment.domain.api.IUserServicePort;
import com.foodcourt.usersmanagment.domain.model.OwnerModel;
import com.foodcourt.usersmanagment.CreatorMocks;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import com.foodcourt.usersmanagment.infrastructure.configuration.PasswordEncoderConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        OwnerRequestDto ownerRequestDto = CreatorMocks.createOwnerRequestDto();
        ownerRequestDto.setPassword("plainPassword");
        OwnerModel ownerModel = CreatorMocks.createOwnerModel();
        ownerModel.setPassword("plainPassword");

        when(userRequestMapper.toOwner(ownerRequestDto)).thenReturn(ownerModel);


        // When
        userHandler.saveUser(ownerRequestDto);

        // Then
        verify(userRequestMapper, times(1)).toOwner(ownerRequestDto);

        verify(userServicePort, times(1)).saveOwner(ownerModel);
        assertEquals("plainPassword", ownerModel.getPassword());
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
}
