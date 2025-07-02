package com.foodcourt.usersmanagment.domain.api;

import com.foodcourt.usersmanagment.domain.model.CreateUserModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;

import java.util.List;

public interface IUserServicePort {

    void saveOwner(CreateUserModel createUserModel);
    UserModel findUserById(Long id);
    Boolean verifyUserRol(String dni, String role);
    void createAccountEmployee(CreateUserModel createUserModel, String ownerDni);
    UserModel getUserByDni(String dni);
    void createAccountCustomer(CreateUserModel createUserModel);
    List<UserModel> findEmployeeByRestaurantId(Long restaurantId);
}
