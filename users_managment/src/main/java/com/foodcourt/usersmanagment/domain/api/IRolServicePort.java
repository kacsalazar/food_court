package com.foodcourt.usersmanagment.domain.api;

import com.foodcourt.usersmanagment.domain.model.RolModel;

public interface IRolServicePort {

    RolModel findByName(String name);
}
