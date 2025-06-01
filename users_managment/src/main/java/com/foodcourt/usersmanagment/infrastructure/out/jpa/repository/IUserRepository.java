package com.foodcourt.usersmanagment.infrastructure.out.jpa.repository;

import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<UserEntity, Long> {

    // Additional query methods can be defined here if needed
    // For example:
    // Optional<UserEntity> findByEmail(String email);
    // List<UserEntity> findByRole(String role);
}
