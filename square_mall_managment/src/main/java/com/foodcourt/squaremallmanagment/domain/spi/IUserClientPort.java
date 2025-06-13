package com.foodcourt.squaremallmanagment.domain.spi;

import com.foodcourt.squaremallmanagment.domain.model.UserModel;

public interface IUserClientPort {

    Boolean isValidUser(String dni, String rol);
    UserModel ownerExists(String dni);
}
