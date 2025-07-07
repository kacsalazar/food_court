package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.api.ITraceabilityServicePort;
import com.foodcourt.squaremallmanagment.domain.exception.NotPermissionException;
import com.foodcourt.squaremallmanagment.domain.model.EmployeeModel;
import com.foodcourt.squaremallmanagment.domain.model.EmployeeRankingModel;
import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderUpdateModel;
import com.foodcourt.squaremallmanagment.domain.model.restaurant.RestaurantModel;
import com.foodcourt.squaremallmanagment.domain.spi.*;
import com.foodcourt.squaremallmanagment.domain.usecase.util.StateEnum;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TraceabilityUseCase implements ITraceabilityServicePort {

    private final ITraceabilityPersistencePort traceabilityPersistencePort;
    private final IOrderPersistencePort orderPersistencePort;
    private final IUserRestPort userClientPort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IEmployeeRestPort employeeRestPort;

    public List<TraceabilityModel> findAllTracesByOrderId(Long orderId, String userDni) {
        validateOrderUser(orderId, userDni);
        return traceabilityPersistencePort.findAllTracesByOrderId(orderId);
    }

    public String getOrderProcessingTime(Long OrderId, String userDni) {

        validateOrderOwnerRestaurant(OrderId, userDni);

        List<TraceabilityModel> traceabilityModels = traceabilityPersistencePort.findAllTracesByOrderId(OrderId);
        TraceabilityModel firstTrace = traceabilityModels.stream()
                .filter(trace -> trace.getNewState().equals(StateEnum.IN_PROGRESS.name()) )
                .findFirst()
                .orElseThrow(NotPermissionException::new);

        TraceabilityModel lastTrace = traceabilityModels.stream()
                .filter(trace -> trace.getNewState().equals(StateEnum.DELIVERED.name()) )
                .findFirst()
                .orElseThrow(NotPermissionException::new);

        return "Elapsed time of " + calculateDuration(firstTrace.getDate(), lastTrace.getDate());
    }


    public List<EmployeeRankingModel> getRankingForOrderByEmployeeId(Long restaurantId, String OwnerDni) {

        validateOwnerRestaurant(restaurantId, OwnerDni);

        List<EmployeeModel> employees = employeeRestPort.getEmployeesByRestaurantId(restaurantId);

        //List<EmployeeRankingModel> ranking =
                return employees.stream()
                .map(employee -> {
                    Long employeeId = employee.getId();

                    List<OrderModel> ordersByEmployee = orderPersistencePort
                            .findAllOrdersByEmployeeId(employeeId).stream()
                            .filter(order -> order.getEmployeeId().equals(employeeId))
                            .toList();

                    List<Duration> durations = ordersByEmployee.stream()
                            .map(order -> {
                                List<TraceabilityModel> traceList = traceabilityPersistencePort.findAllByOrderIdAndStatus(order.getId());
                                if (traceList == null) return null;

                                LocalDateTime start = traceList.stream()
                                        .filter(t -> StateEnum.IN_PROGRESS.name().equals(t.getNewState()))
                                        .map(TraceabilityModel::getDate)
                                        .findFirst()
                                        .orElse(null);

                                LocalDateTime end = traceList.stream()
                                        .filter(t -> StateEnum.DELIVERED.name().equals(t.getNewState()))
                                        .map(TraceabilityModel::getDate)
                                        .findFirst()
                                        .orElse(null);

                                if (start != null && end != null) {
                                    return Duration.between(start, end);
                                } else {
                                    return null;
                                }
                            })
                            .filter(Objects::nonNull)
                            .toList();

                    double avgSeconds = durations.stream()
                            .mapToLong(Duration::getSeconds)
                            .average()
                            .orElse(0);

                    return new EmployeeRankingModel(employeeId, avgSeconds);
                })
                .sorted(Comparator.comparingDouble(EmployeeRankingModel::getAverageSeconds))
                .collect(Collectors.toList());

        //return ranking;
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

    //valida si el propiertario es igual al id del del propietario del restaurante
    private void validateOwnerRestaurant(Long restaurantId, String userDni) {
        RestaurantModel restaurant = restaurantPersistencePort.findRestaurantById(restaurantId);
        if (!restaurant.getIdOwner().equals(userClientPort.ownerExists(userDni).getId())) {
            throw new NotPermissionException();
        }
    }
}
