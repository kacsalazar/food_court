package com.foodcourt.traceabilitymanagement.infrastructure.out.jpa.repository;

import com.foodcourt.traceabilitymanagement.infrastructure.out.jpa.entity.TraceabilityEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ITraceabilityRepository extends MongoRepository<TraceabilityEntity, String> {

    @Query("{ 'orderId' : ?0 }")
    List<TraceabilityEntity> findAllByOrderId(Long orderId);

    @Query("{ 'orderId': ?0, 'newState': { $in: ?1 } }")
    List<TraceabilityEntity> findByOrderIdAndNewStateIn(Long orderId, Collection<String> states);

}
