package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository;

import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IRestaurantRepository extends CrudRepository<RestaurantEntity, Long> {

    @Query("SELECT * FROM restaurants ORDER BY name ASC LIMIT :size OFFSET :page")
    List<RestaurantEntity> findAllByOrderByIdAsc(Integer page, Integer size);

    @Query("SELECT * FROM restaurants r WHERE r.id_owner = :idOwner")
    RestaurantEntity findRestaurantByIdOwner(Long idOwner);

}
