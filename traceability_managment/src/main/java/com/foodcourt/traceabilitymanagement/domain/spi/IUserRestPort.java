package com.foodcourt.traceabilitymanagement.domain.spi;

import com.foodcourt.traceabilitymanagement.domain.model.user.UserModel;

public interface IUserRestPort {
    UserModel ownerExists(String dni);
}
