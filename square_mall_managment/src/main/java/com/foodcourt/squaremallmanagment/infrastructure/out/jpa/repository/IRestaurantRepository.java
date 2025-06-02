package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository;

import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.repository.CrudRepository;

public interface IRestaurantRepository extends CrudRepository<RestaurantEntity, Long> {

}
