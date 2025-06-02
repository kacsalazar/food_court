package com.foodcourt.usersmanagment.application.handler.impl;

import com.foodcourt.usersmanagment.application.dto.request.OwnerRequestDto;
import com.foodcourt.usersmanagment.application.mapper.IUserRequestMapper;
import com.foodcourt.usersmanagment.domain.api.IUserServicePort;
import com.foodcourt.usersmanagment.domain.model.OwnerModel;
import com.foodcourt.usersmanagment.CreatorMocks;
import com.foodcourt.usersmanagment.infrastructure.configuration.PasswordEncoderConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

public class UserHandlerTest {

    @Mock
    IUserServicePort userServicePort;

    @Mock
    IUserRequestMapper userRequestMapper;

    @Mock
    PasswordEncoderConfig passwordEncoderConfig;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserHandler userHandler;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOwner() {
        //given
        OwnerModel ownerModel = CreatorMocks.createOwnerModel();
        OwnerRequestDto ownerRequestDto = CreatorMocks.createOwnerRequestDto();

        //when
        when(userRequestMapper.toOwner(ownerRequestDto)).thenReturn(ownerModel);
        when(passwordEncoderConfig.passwordEncoder()).thenReturn(passwordEncoder);
        when(passwordEncoder.encode(ownerModel.getPassword())).thenReturn("encodedPassword");

        //Then
        userHandler.saveUser(ownerRequestDto);

        verify(userServicePort, times(1)).saveOwner(ownerModel);
    }
}
