package com.foodcourt.usersmanagment.domain.spi;

import com.foodcourt.usersmanagment.domain.model.OwnerModel;

public interface IUserPersistencePort {

    OwnerModel saveOwner(OwnerModel ownerModel);
}
