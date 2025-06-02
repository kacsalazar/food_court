package com.foodcourt.usersmanagment.infrastructure.out.jpa.repository;

import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.RolEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface IRolRepository extends CrudRepository<RolEntity, Long> {

    @Query("SELECT * FROM roles r WHERE r.name = :name")
    RolEntity findByName(@Param("name") String name);
}
