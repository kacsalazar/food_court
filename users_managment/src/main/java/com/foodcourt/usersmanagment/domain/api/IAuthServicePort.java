package com.foodcourt.usersmanagment.domain.api;

import com.foodcourt.usersmanagment.domain.model.AuthModel;

public interface IAuthServicePort {

    void userLogin(AuthModel authModel);
}
