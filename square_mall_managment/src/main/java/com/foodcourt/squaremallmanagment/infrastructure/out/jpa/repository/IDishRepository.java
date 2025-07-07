package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository;

import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.DishEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDishRepository extends CrudRepository<DishEntity, Long> {
    // Additional query methods can be defined here if needed
// ORDER BY name ASC LIMIT :size OFFSET :offset
    @Query(value = "SELECT * FROM dishes WHERE id_restaurant = :idRestaurant AND (:idCategory IS NULL OR id_category = :idCategory) " +
            "ORDER BY name ASC LIMIT :size OFFSET :offset")
    List<DishEntity> findDishesByRestaurant(
            @Param("idRestaurant") Long idRestaurant,
            @Param("idCategory") Long idCategory,
            @Param("offset") int offset,
            @Param("size") int size
    );
}
