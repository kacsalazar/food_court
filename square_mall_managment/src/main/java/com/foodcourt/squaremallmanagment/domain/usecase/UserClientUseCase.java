package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.api.IUserClientServicePort;
import com.foodcourt.squaremallmanagment.domain.spi.IUserClientPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserClientUseCase implements IUserClientServicePort {

    private final IUserClientPort userClientPort;

    @Override
    public Boolean isValidUser(String dni, String rol) {
        return userClientPort.isValidUser(dni, rol);
    }
}
