package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.api.ITraceabilityServicePort;
import com.foodcourt.squaremallmanagment.domain.exception.NotPermissionException;
import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderUpdateModel;
import com.foodcourt.squaremallmanagment.domain.model.restaurant.RestaurantModel;
import com.foodcourt.squaremallmanagment.domain.spi.IOrderPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.ITraceabilityPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IUserRestPort;
import com.foodcourt.squaremallmanagment.domain.usecase.util.StateEnum;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@RequiredArgsConstructor
public class TraceabilityUseCase implements ITraceabilityServicePort {

    private final ITraceabilityPersistencePort traceabilityPersistencePort;
    private final IOrderPersistencePort orderPersistencePort;
    private final IUserRestPort userClientPort;
    private final IRestaurantPersistencePort restaurantPersistencePort;

    public List<TraceabilityModel> findAllTracesByOrderId(Long orderId, String userDni) {
        validateOrderUser(orderId, userDni);
        return traceabilityPersistencePort.findAllTracesByOrderId(orderId);
    }

    public String getOrderProcessingTime(Long OrderId, String userDni) {

        validateOrderOwnerRestaurant(OrderId, userDni);

        List<TraceabilityModel> traceabilityModels = traceabilityPersistencePort.findAllTracesByOrderId(OrderId);
        TraceabilityModel firstTrace = traceabilityModels.stream()
                .filter(trace -> trace.getNewState().equals(StateEnum.PENDING.name()) )
                .findFirst()
                .orElseThrow(NotPermissionException::new);

        TraceabilityModel lastTrace = traceabilityModels.stream()
                .filter(trace -> trace.getNewState().equals(StateEnum.DELIVERED.name()) )
                .findFirst()
                .orElseThrow(NotPermissionException::new);

        return "Elapsed time of " + calculateDuration(firstTrace.getDate(), lastTrace.getDate());
    }

    private void validateOrderUser(Long OrderId, String userDni) {
        OrderUpdateModel order = orderPersistencePort.findOrderById(OrderId);
        if (order == null || !order.getIdClient().equals(userClientPort.ownerExists(userDni).getId())) {
            throw new NotPermissionException();
        }
    }

    private String calculateDuration(LocalDateTime start, LocalDateTime end) {

        Duration time = Duration.between(start, end);
        long totalSeconds = time.getSeconds();
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        return  String.format("%02d hours, %02d minutes, %02d seconds", hours, minutes, seconds);

    }

    private void validateOrderOwnerRestaurant(Long orderId, String userDni) {
        OrderUpdateModel order = orderPersistencePort.findOrderById(orderId);
        RestaurantModel restaurant = restaurantPersistencePort.findRestaurantById(order.getIdRestaurant());

        if(!restaurant.getIdOwner().equals(userClientPort.ownerExists(userDni).getId())) {
            throw new NotPermissionException();
        }
    }
}
