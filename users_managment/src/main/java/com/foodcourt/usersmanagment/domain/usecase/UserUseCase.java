package com.foodcourt.usersmanagment.domain.usecase;

import com.foodcourt.usersmanagment.domain.api.IUserServicePort;
import com.foodcourt.usersmanagment.domain.model.OwnerModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import com.foodcourt.usersmanagment.domain.spi.IUserPersistencePort;
import com.foodcourt.usersmanagment.domain.util.UseValidationUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {

    //pregutar como se inyecta UseValidationUtil como debo manejarlo
    private final UseValidationUtil useValidationUtil;
    private final IUserPersistencePort userPersistencePort;

    @Override
    public void saveOwner(OwnerModel ownerModel) {
        useValidationUtil.isValidUser(ownerModel);
        userPersistencePort.saveOwner(ownerModel);
    }

    @Override
    public UserModel findUserById(Long id) {
        return userPersistencePort.findUserById(id);
    }

    @Override
    public Boolean verifyUserRol(Long id, String role) {
        return userPersistencePort.verifyUserRol(id, role);
    }
}
