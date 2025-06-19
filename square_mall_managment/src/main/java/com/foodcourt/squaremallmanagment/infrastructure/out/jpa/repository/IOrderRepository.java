package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository;

import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderRepository extends CrudRepository<OrderEntity, Long> {

    @Query("SELECT o FROM OrderEntity o WHERE o.idClient = :idClient AND o.status IN" +
            " ('IN_PROGRESS', 'PENDING', 'ALREADY')")
    List<OrderEntity> findOrdersByIdClient(@Param("idClient") Long idClient);
}
