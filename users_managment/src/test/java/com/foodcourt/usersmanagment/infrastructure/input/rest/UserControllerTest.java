package com.foodcourt.usersmanagment.infrastructure.input.rest;

import com.foodcourt.usersmanagment.application.dto.request.OwnerRequestDto;
import com.foodcourt.usersmanagment.application.dto.request.UserRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.UserResponseDto;
import com.foodcourt.usersmanagment.application.handler.IUserHandler;
import com.foodcourt.usersmanagment.CreatorMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class UserControllerTest {

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


    @Test
    void testCreateAccountEmployee() {
        OwnerRequestDto userRequestDto = CreatorMocks.createEmployeeRequestDto();

        ResponseEntity<Void> response = userRestController.createAccountEmployee(userRequestDto);

        verify(userHandler, times(1)).createAccountEmployee(userRequestDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testGetUserByDni() {
        String dni = "123";
        UserResponseDto userResponseDto = CreatorMocks.createUserResponseDto();
        when(userHandler.getUserByDni(dni)).thenReturn(userResponseDto);

        ResponseEntity<UserResponseDto> response = userRestController.getUserByDni(dni);

        verify(userHandler, times(1)).getUserByDni(dni);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponseDto, response.getBody());
    }

    @Test
    void testCreateAccountCustomer() {
        UserRequestDto userRequestDto = CreatorMocks.createOwnerRequestDto();

        ResponseEntity<Void> response = userRestController.createAccountCustomer(userRequestDto);

        verify(userHandler, times(1)).createAccountCustomer(userRequestDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void employeesByRestaurantIdSuccessfully() {
        // Arrange
        Long restaurantId = 1L;
        UserResponseDto userResponse = UserResponseDto.builder()
                .id(1L)
                .dni("123")
                .name("Jane Doe")
                .phoneNumber("123456789")
                .build();

        List<UserResponseDto> mockUsers = List.of(userResponse);
        when(userHandler.findEmployeeByRestaurantId(restaurantId)).thenReturn(mockUsers);

        // Act
        ResponseEntity<List<UserResponseDto>> response = userRestController.findEmployeeByRestaurantId(restaurantId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0)).isEqualTo(userResponse);

        verify(userHandler).findEmployeeByRestaurantId(restaurantId);
    }
}
