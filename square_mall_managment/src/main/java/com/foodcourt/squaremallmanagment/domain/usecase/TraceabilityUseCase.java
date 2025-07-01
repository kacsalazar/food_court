package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.api.ITraceabilityServicePort;
import com.foodcourt.squaremallmanagment.domain.exception.NotPermissionException;
import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderUpdateModel;
import com.foodcourt.squaremallmanagment.domain.spi.IOrderPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.ITraceabilityPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IUserRestPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TraceabilityUseCase implements ITraceabilityServicePort {

    private final ITraceabilityPersistencePort traceabilityPersistencePort;
    private final IOrderPersistencePort orderPersistencePort;
    private final IUserRestPort userClientPort;

    public List<TraceabilityModel> findAllTracesByOrderId(Long orderId, String userDni) {
        validateOrderUser(orderId, userDni);
        return traceabilityPersistencePort.findAllTracesByOrderId(orderId);
    }

    private void validateOrderUser(Long OrderId, String userDni) {
        OrderUpdateModel order = orderPersistencePort.findOrderById(OrderId);
        if (order == null || !order.getIdClient().equals(userClientPort.ownerExists(userDni).getId())) {
            throw new NotPermissionException();
        }
    }
}
