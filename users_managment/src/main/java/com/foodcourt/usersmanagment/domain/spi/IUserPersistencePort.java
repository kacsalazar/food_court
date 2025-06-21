package com.foodcourt.usersmanagment.domain.spi;

import com.foodcourt.usersmanagment.domain.model.CreateUserModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;

public interface IUserPersistencePort {

    void saveUser(CreateUserModel user);
    UserModel findUserById(Long id);
    UserModel findUserByEmail(String email);
    UserModel findUserByDni(String dni);

}
