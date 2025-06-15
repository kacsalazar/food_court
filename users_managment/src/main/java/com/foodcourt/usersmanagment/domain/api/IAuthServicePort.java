package com.foodcourt.usersmanagment.domain.api;

import com.foodcourt.usersmanagment.domain.model.AuthModel;
import com.foodcourt.usersmanagment.domain.model.ClaimUserModel;
import com.foodcourt.usersmanagment.domain.model.TokenModel;

public interface IAuthServicePort {

    TokenModel userLogin(AuthModel authModel);
}
