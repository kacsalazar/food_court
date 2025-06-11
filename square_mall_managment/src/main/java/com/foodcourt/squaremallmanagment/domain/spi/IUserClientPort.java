package com.foodcourt.squaremallmanagment.domain.spi;

public interface IUserClientPort {

    Boolean isValidUser(String dni, String rol);
}
