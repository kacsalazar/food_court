package com.foodcourt.usersmanagment.domain.spi;

import com.foodcourt.usersmanagment.domain.model.CreateUserModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;

import java.util.List;

public interface IUserPersistencePort {

    void saveUser(CreateUserModel user);
    UserModel findUserById(Long id);
    UserModel findUserByEmail(String email);
    UserModel findUserByDni(String dni);
    List<UserModel> findEmployeeByRestaurantId(Long restaurantId);

}
