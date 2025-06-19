package com.foodcourt.usersmanagment.domain.usecase;

import com.foodcourt.usersmanagment.domain.api.IUserServicePort;
import com.foodcourt.usersmanagment.domain.exception.ConstantException;
import com.foodcourt.usersmanagment.domain.exception.DomainException;
import com.foodcourt.usersmanagment.domain.model.SaveUserModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import com.foodcourt.usersmanagment.domain.spi.IUserPersistencePort;
import com.foodcourt.usersmanagment.domain.usecase.util.UseValidationUtil;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

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
        return Optional.ofNullable(userPersistencePort.findUserById(id))
                .orElseThrow(() -> new DomainException(ConstantException.USER_NOT_FOUND));
    }

    @Override
    public Boolean verifyUserRol(String dni, String role) {
        UserModel user = userPersistencePort.findUserByDni(dni);
        if (user == null)
            throw new DomainException(ConstantException.USER_NOT_FOUND);

        return Optional.ofNullable(userPersistencePort.verifyUserRol(user, role))
                .orElseThrow(() -> new DomainException(ConstantException.INVALID_USER));
    }

    @Override
    public void createAccountEmployee(SaveUserModel saveUserModel) {
        UseValidationUtil.isValidUser(saveUserModel);
        userPersistencePort.createAccountEmployee(saveUserModel);
    }

    @Override
    public UserModel getUserByDni(String dni) {
        return Optional.ofNullable(userPersistencePort.findUserByDni(dni))
                .orElseThrow(() -> new DomainException(ConstantException.USER_NOT_FOUND));
    }

    @Override
    public void createAccountCustomer(SaveUserModel saveUserModel) {
        UseValidationUtil.isValidUser(saveUserModel);
        userPersistencePort.createAccountCustomer(saveUserModel);
    }

}
