package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository;

import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.OrderEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.OrderVsDishEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderVsDishRepository extends CrudRepository<OrderVsDishEntity, Long> {

    @Query("SELECT * FROM order_items o WHERE o.id_order = :idOrder")
    List<OrderVsDishEntity> findByOrderId(@Param("idOrder") Long idOrder);


}
