package com.foodcourt.usersmanagment.domain.usecase;

import com.foodcourt.usersmanagment.domain.api.IUserServicePort;
import com.foodcourt.usersmanagment.domain.model.OwnerModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import com.foodcourt.usersmanagment.domain.spi.IUserPersistencePort;
import com.foodcourt.usersmanagment.domain.usecase.util.UseValidationUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    @Override
    public void saveOwner(OwnerModel ownerModel) {
        UseValidationUtil.isValidUser(ownerModel);
        userPersistencePort.saveOwner(ownerModel);
    }

    @Override
    public UserModel findUserById(Long id) {
        return userPersistencePort.findUserById(id);
    }

    @Override
    public Boolean verifyUserRol(String dni, String role) {
        return userPersistencePort.verifyUserRol(dni, role);
    }
}
