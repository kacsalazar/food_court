package com.foodcourt.usersmanagment.domain.usecase;

import com.foodcourt.usersmanagment.domain.api.IUserServicePort;
import com.foodcourt.usersmanagment.domain.model.SaveUserModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import com.foodcourt.usersmanagment.domain.spi.IUserPersistencePort;
import com.foodcourt.usersmanagment.domain.usecase.util.UseValidationUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    @Override
    public void saveOwner(SaveUserModel saveUserModel) {
        UseValidationUtil.isValidUser(saveUserModel);
        userPersistencePort.saveOwner(saveUserModel);
    }

    @Override
    public UserModel findUserById(Long id) {
        return userPersistencePort.findUserById(id);
    }

    @Override
    public Boolean verifyUserRol(String dni, String role) {
        return userPersistencePort.verifyUserRol(dni, role);
    }

    @Override
    public void createAccountEmployee(SaveUserModel saveUserModel) {
        UseValidationUtil.isValidUser(saveUserModel);
        userPersistencePort.createAccountEmployee(saveUserModel);
    }

    @Override
    public UserModel findUserById(String dni) {
        return userPersistencePort.findUserByDni(dni);
    }

}
