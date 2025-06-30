package com.foodcourt.squaremallmanagment.domain.spi;

import com.foodcourt.squaremallmanagment.domain.model.UserModel;

public interface IUserRestPort {

    Boolean isValidUser(String dni, String rol);
    UserModel ownerExists(String dni);
    UserModel getUserById(Long id);
}
