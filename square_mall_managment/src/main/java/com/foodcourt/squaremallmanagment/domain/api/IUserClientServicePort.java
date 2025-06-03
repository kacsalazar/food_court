package com.foodcourt.squaremallmanagment.domain.api;

public interface IUserClientServicePort {

    Boolean isValidUser(Long userId, String rol);
}
