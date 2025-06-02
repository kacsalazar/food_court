package com.foodcourt.usersmanagment.domain.spi;

import com.foodcourt.usersmanagment.domain.model.RolModel;

public interface IRolPersistencePort {

    RolModel findByName(String name);
    RolModel findById(Long id);
}
