package com.foodcourt.usersmanagment.domain.spi;

import com.foodcourt.usersmanagment.domain.model.SaveUserModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;

public interface IUserPersistencePort {

    SaveUserModel saveOwner(SaveUserModel saveUserModel);
    UserModel findUserById(Long id);
    Boolean verifyUserRol(UserModel user, String role);
    UserModel findUserByEmail(String email);
    UserModel findUserByDni(String dni);
    void createAccountEmployee(SaveUserModel saveUserModel);
    void createAccountCustomer(SaveUserModel saveUserModel);

}
