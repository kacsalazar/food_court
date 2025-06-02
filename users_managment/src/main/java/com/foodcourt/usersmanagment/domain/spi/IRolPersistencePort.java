package com.foodcourt.usersmanagment.domain.spi;

import com.foodcourt.usersmanagment.domain.model.GetRolModel;

public interface IRolPersistencePort {

    GetRolModel findByName(String name);
}
