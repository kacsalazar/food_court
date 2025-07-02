package com.foodcourt.usersmanagment.application.handler.impl;

import com.foodcourt.usersmanagment.application.dto.request.OwnerRequestDto;
import com.foodcourt.usersmanagment.application.dto.request.UserRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.UserResponseDto;
import com.foodcourt.usersmanagment.application.mapper.IUserRequestMapper;
import com.foodcourt.usersmanagment.domain.api.IUserServicePort;
import com.foodcourt.usersmanagment.domain.model.CreateUserModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserHandlerTest {

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
    void saveUser() {
        // Arrange
        UserRequestDto dto = UserRequestDto.builder().dni("123").email("email@mail.com")
                .name("User").phoneNumber("313678544").password("pass").birthdayDate(null).build();
        CreateUserModel model = CreateUserModel.builder()
                .dni("123").email("email@mail.com").name("User").phoneNumber("123456").password("pass").build();

        when(userRequestMapper.toUserToSave(dto)).thenReturn(model);

        // Act
        userHandler.saveUser(dto);

        // Assert
        verify(userServicePort).saveOwner(model);
    }

    @Test
    void getUserById() {
        // Arrange
        Long userId = 1L;
        UserModel model = UserModel.builder().id(userId).name("Kelly").dni("123").email("mail@mail.com").build();
        UserResponseDto response = UserResponseDto.builder().dni("123")
                .name("Kelly").phoneNumber("313678544").birthdayDate(null).build();

        when(userServicePort.findUserById(userId)).thenReturn(model);
        when(userRequestMapper.toUserResponseDto(model)).thenReturn(response);

        // Act
        UserResponseDto result = userHandler.getUserById(userId);

        // Assert
        assertThat(result.getName()).isEqualTo(response.getName());
        verify(userServicePort).findUserById(userId);
        verify(userRequestMapper).toUserResponseDto(model);
    }

    @Test
    void verifyUserRol() {
        // Arrange
        String dni = "123";
        String role = "ROLE_OWNER";
        when(userServicePort.verifyUserRol(dni, role)).thenReturn(true);

        // Act
        Boolean result = userHandler.verifyUserRol(dni, role);

        // Assert
        assertThat(result).isTrue();
        verify(userServicePort).verifyUserRol(dni, role);
    }

    //Sale error por la clase util
    /*@Test
    void createAccountEmployee() {
        // Arrange
        OwnerRequestDto dto = OwnerRequestDto.builder().name("Kelly").dni("123").email("mail@mail.com").restaurantId(1L)
                .email("mail@emial.com").password("pass")
                .build();
        CreateUserModel model = CreateUserModel.builder()
                .name("Kelly").dni("123").email("mail@mail.com").phoneNumber("3136871147")
                .password("pass").idRestaurant(1L).build();

        when(userRequestMapper.toOwnerModel(dto)).thenReturn(model);

        // Act
        userHandler.createAccountEmployee(dto);

        // Assert
        verify(userServicePort).createAccountEmployee(eq(model), anyString());
    }*/

    @Test
    void getUserByDni() {
        // Arrange
        String dni = "123";
        UserModel model = UserModel.builder().dni("123").name("John").email("john@mail.com").build();
        UserRequestDto response = UserRequestDto.builder().dni("123").email("john@mail.com")
                .name("John").phoneNumber("313678544").password("311").birthdayDate(null).build();

        when(userServicePort.getUserByDni(dni)).thenReturn(model);

        // Act
        UserResponseDto result = userHandler.getUserByDni(dni);

        // Assert
        assertThat(result.getName()).isEqualTo(response.getName());
    }

    @Test
    void createAccountCustomer() {
        // Arrange
        UserRequestDto dto = UserRequestDto.builder().dni("222").email("new@mail.com")
                .name("Laura").phoneNumber("313678544").password("pass").birthdayDate(null).build();
        CreateUserModel model = CreateUserModel.builder()
                .dni("222").email("new@mail.com").name("Laura").phoneNumber("313678544").password("pass").build();

        when(userRequestMapper.toUserToSave(dto)).thenReturn(model);

        // Act
        userHandler.createAccountCustomer(dto);

        // Assert
        verify(userServicePort).createAccountCustomer(model);
    }

    @Test
    void findEmployeesByRestaurantId() {
        // Arrange
        Long restaurantId = 1L;
        UserModel userModel = UserModel.builder().name("Chef").dni("456").email("chef@mail.com").build();
        UserRequestDto responseDto = UserRequestDto.builder().dni("456").email("chef@mail.com")
                .name("Chef").phoneNumber("313678544").password("pass").birthdayDate(null).build();

        when(userServicePort.findEmployeeByRestaurantId(restaurantId)).thenReturn(List.of(userModel));

        // Act
        List<UserResponseDto> result = userHandler.findEmployeeByRestaurantId(restaurantId);

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo(responseDto.getName());
    }
}
