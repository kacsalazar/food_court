package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;
import com.foodcourt.squaremallmanagment.domain.spi.ITraceabilityPersistencePort;
import com.foodcourt.squaremallmanagment.domain.usecase.util.StatusEnum;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.TraceabilityEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.impl.TraceabilityEntityMapper;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.ITraceabilityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

        List<TraceabilityEntity> traceabilityEntities =
                traceabilityRepository.findByOrderIdAndStates(OrderId,
                        List.of(StatusEnum.IN_PROGRESS.name(), StatusEnum.DELIVERED.name()));
        return traceabilityEntities.stream()
                .map(TraceabilityEntityMapper::toTraceabilityModel)
                .toList();
    }


}
