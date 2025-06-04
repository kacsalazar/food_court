package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository;

import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.DishEntity;
import org.springframework.data.repository.CrudRepository;

public interface IDishRepository extends CrudRepository<DishEntity, Long> {
    // Additional query methods can be defined here if needed
}
