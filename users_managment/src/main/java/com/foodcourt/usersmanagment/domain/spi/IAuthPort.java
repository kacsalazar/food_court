package com.foodcourt.usersmanagment.domain.spi;

import com.foodcourt.usersmanagment.domain.model.AuthModel;

public interface IAuthPort {
    void userLogin(AuthModel authModel);
}
