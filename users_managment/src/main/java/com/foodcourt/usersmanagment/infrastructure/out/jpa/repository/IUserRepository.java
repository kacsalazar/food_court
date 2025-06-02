package com.foodcourt.usersmanagment.infrastructure.out.jpa.repository;

import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<UserEntity, Long> {

}
