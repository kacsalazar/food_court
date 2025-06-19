package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository;

import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.OrderVsDishEntity;
import org.springframework.data.repository.CrudRepository;

public interface IOrderVsDishRepository extends CrudRepository<OrderVsDishEntity, Long> {

}
