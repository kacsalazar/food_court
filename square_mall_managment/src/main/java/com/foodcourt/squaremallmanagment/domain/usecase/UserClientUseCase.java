package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.api.IUserClientServicePort;
import com.foodcourt.squaremallmanagment.domain.exception.ConstantException;
import com.foodcourt.squaremallmanagment.domain.exception.DomainException;
import com.foodcourt.squaremallmanagment.domain.spi.IUserClientPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UserClientUseCase implements IUserClientServicePort {

    private final IUserClientPort userClientPort;

    @Override
    public Boolean isValidUser(String dni, String rol) {

        return Optional.ofNullable(userClientPort.isValidUser(dni, rol))
                .orElseThrow(() -> new DomainException(ConstantException.INVALID_USER));
        //return userClientPort.isValidUser(dni, rol);
    }
}
