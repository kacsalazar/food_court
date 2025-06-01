package com.foodcourt.usersmanagment.domain.api;

import com.foodcourt.usersmanagment.domain.model.OwnerModel;

public interface IUserServicePort {

    void saveOwner(OwnerModel ownerModel);
}
