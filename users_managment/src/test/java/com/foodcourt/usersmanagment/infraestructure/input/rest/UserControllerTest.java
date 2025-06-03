package com.foodcourt.usersmanagment.infraestructure.input.rest;

import com.foodcourt.usersmanagment.application.dto.request.OwnerRequestDto;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserControllerTest {

    @Mock
    IUserHandler userHandler;

    @InjectMocks
    UserRestController userRestController;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {

        OwnerRequestDto ownerRequestDto = CreatorMocks.createOwnerRequestDto();

        ResponseEntity<Void> response = userRestController.saveUser(ownerRequestDto);

        verify(userHandler, times(1)).saveUser(ownerRequestDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

}
