package com.foodcourt.usersmanagment.infrastructure.out.jpa.repository;

import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends CrudRepository<UserEntity, Long> {

    @Query("SELECT * FROM users u WHERE u.email = :email")
    UserEntity findUserByEmail(@Param("email") String email);

    @Query("SELECT * FROM users u WHERE u.dni = :dni")
    UserEntity findUserByDni(@Param("dni") String dni);

    @Query("SELECT * FROM users u WHERE u.id_restaurant = :restaurantId")
    List<UserEntity> findEmployeeByRestaurantId(@Param("restaurantId") Long restaurantId);

}
