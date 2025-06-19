package com.foodcourt.usersmanagment.domain.api;

import com.foodcourt.usersmanagment.domain.model.SaveUserModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;

public interface IUserServicePort {

    void saveOwner(SaveUserModel saveUserModel);
    UserModel findUserById(Long id);
    Boolean verifyUserRol(String dni, String role);
    void createAccountEmployee(SaveUserModel saveUserModel);
    UserModel getUserByDni(String dni);
    void createAccountCustomer(SaveUserModel saveUserModel);
}
