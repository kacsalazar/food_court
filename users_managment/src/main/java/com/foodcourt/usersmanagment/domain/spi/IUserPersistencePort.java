package com.foodcourt.usersmanagment.domain.spi;

import com.foodcourt.usersmanagment.domain.model.OwnerModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;

public interface IUserPersistencePort {

    OwnerModel saveOwner(OwnerModel ownerModel);
    UserModel findUserById(Long id);
    Boolean verifyUserRol(String dni, String role);
    UserModel findUserByEmail(String email);
    UserModel findUserByDni(String dni);
}
