package com.foodcourt.usersmanagment.domain.spi;

import com.foodcourt.usersmanagment.domain.model.AuthModel;
import com.foodcourt.usersmanagment.domain.model.ClaimUserModel;
import com.foodcourt.usersmanagment.domain.model.TokenModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;

public interface IAuthPort {
    TokenModel userLogin(AuthModel authModel, UserModel user, String rolName);
    Boolean verifyPassword(String password, String encodedPassword);
}
