package com.foodcourt.squaremallmanagment.domain.spi;

public interface IUserClientPort {

    Boolean isValidUser(Long userId, String rol);
}
