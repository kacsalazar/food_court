package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository;

import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.TraceabilityEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITraceabilityRepository extends MongoRepository<TraceabilityEntity, String> {

    @Query("{ 'orderId' : ?0 }")
    List<TraceabilityEntity> findAllByOrderId(Long orderId);

}
