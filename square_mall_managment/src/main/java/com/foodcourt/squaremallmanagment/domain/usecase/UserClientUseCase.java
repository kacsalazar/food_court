package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.api.IUserClientServicePort;
import com.foodcourt.squaremallmanagment.domain.spi.IUserRestPort;
import com.foodcourt.squaremallmanagment.domain.exception.InvalidUserException;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UserClientUseCase implements IUserClientServicePort {

    private final IUserRestPort userClientPort;

    @Override
    public Boolean isValidUser(String dni, String rol) {

        return Optional.ofNullable(userClientPort.isValidUser(dni, rol))
                .orElseThrow(InvalidUserException::new);
    }
}
