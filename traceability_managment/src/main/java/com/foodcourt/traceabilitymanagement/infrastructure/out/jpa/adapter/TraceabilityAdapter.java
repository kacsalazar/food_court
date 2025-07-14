package com.foodcourt.traceabilitymanagement.infrastructure.out.jpa.adapter;

import com.foodcourt.traceabilitymanagement.domain.model.TraceabilityModel;
import com.foodcourt.traceabilitymanagement.domain.spi.ITraceabilityPersistencePort;
import com.foodcourt.traceabilitymanagement.domain.usecase.util.StatusEnum;
import com.foodcourt.traceabilitymanagement.infrastructure.out.jpa.entity.TraceabilityEntity;
import com.foodcourt.traceabilitymanagement.infrastructure.out.jpa.mapper.TraceabilityEntityMapper;
import com.foodcourt.traceabilitymanagement.infrastructure.out.jpa.repository.ITraceabilityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TraceabilityAdapter implements ITraceabilityPersistencePort {

    private final ITraceabilityRepository traceabilityRepository;

    public void saveTraceability(TraceabilityModel traceabilityModel) {
        TraceabilityEntity traceabilityEntity = TraceabilityEntityMapper.toTraceabilityEntity(traceabilityModel);
        traceabilityRepository.save(traceabilityEntity);
    }

    public List<TraceabilityModel> findAllTracesByOrderId(Long orderId) {
        List<TraceabilityEntity> traceabilityEntities = traceabilityRepository.findAllByOrderId(orderId);
        return traceabilityEntities.stream()
                .map(TraceabilityEntityMapper::toTraceabilityModel)
                .toList();
    }

    public List<TraceabilityModel> findAllByOrderIdAndStatus(Long OrderId){
        Collection<String> status  = List.of(
                StatusEnum.IN_PROGRESS.name(),
                StatusEnum.DELIVERED.name()
        );
        List<TraceabilityEntity> traceabilityEntities =
                traceabilityRepository.findByOrderIdAndNewStateIn(OrderId,
                        status);
        log.info("TrAZABILIDADES"+ traceabilityEntities);
        log.info("TrAZABILIDADES"+ OrderId);
        return traceabilityEntities.stream()
                .map(TraceabilityEntityMapper::toTraceabilityModel)
                .toList();
    }

}
