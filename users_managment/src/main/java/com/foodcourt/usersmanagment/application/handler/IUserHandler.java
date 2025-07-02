package com.foodcourt.usersmanagment.application.handler;

import com.foodcourt.usersmanagment.application.dto.request.OwnerRequestDto;
import com.foodcourt.usersmanagment.application.dto.request.UserRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.UserResponseDto;

import java.util.List;

public interface IUserHandler {

    void saveUser(UserRequestDto userRequestDto);
    UserResponseDto getUserById(Long idOwner);
    Boolean verifyUserRol(String dni, String rol);
    void createAccountEmployee(OwnerRequestDto userRequestDto);
    UserResponseDto getUserByDni(String dni);
    void createAccountCustomer(UserRequestDto userRequestDto);
    List<UserResponseDto> findEmployeeByRestaurantId(Long restaurantId);
}
