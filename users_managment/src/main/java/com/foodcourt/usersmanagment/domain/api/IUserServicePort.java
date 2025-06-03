package com.foodcourt.usersmanagment.domain.api;

import com.foodcourt.usersmanagment.domain.model.OwnerModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;

public interface IUserServicePort {

    void saveOwner(OwnerModel ownerModel);
    UserModel findUserById(Long id);
    Boolean verifyUserRol(Long id, String role);
}
