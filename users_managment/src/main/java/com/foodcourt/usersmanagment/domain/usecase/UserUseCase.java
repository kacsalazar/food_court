package com.foodcourt.usersmanagment.domain.usecase;

import com.foodcourt.usersmanagment.domain.api.IUserServicePort;
import com.foodcourt.usersmanagment.domain.model.OwnerModel;
import com.foodcourt.usersmanagment.domain.spi.IUserPersistencePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    @Override
    public void saveOwner(OwnerModel ownerModel) {
        userPersistencePort.saveOwner(ownerModel);
    }
}
