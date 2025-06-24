package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository;

import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderRepository extends CrudRepository<OrderEntity, Long> {

    @Query("SELECT * FROM orders o WHERE o.id_client = :idClient AND o.status IN" +
            " ('IN_PROGRESS', 'PENDING', 'ALREADY')")
    List<OrderEntity> findOrdersByIdClient(@Param("idClient") Long idClient);

    @Query("SELECT * FROM orders o WHERE o.id_chef = :idChef AND (:status IS NULL OR status = :status) " +
            "LIMIT :size OFFSET :page")
    List<OrderEntity> findOrdersByStatus(@Param("idChef") Long idChef, @Param("status") String status,@Param("size") int size,
                                         @Param("page") int page);
}
