package com.foodcourt.usersmanagment.domain.spi;

import com.foodcourt.usersmanagment.domain.model.OwnerModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;

public interface IUserPersistencePort {

    OwnerModel saveOwner(OwnerModel ownerModel);
    UserModel findUserById(Long id);
    Boolean verifyUserRol(Long id, String role);
}
