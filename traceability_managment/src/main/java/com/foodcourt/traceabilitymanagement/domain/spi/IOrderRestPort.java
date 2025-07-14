package com.foodcourt.traceabilitymanagement.domain.spi;

import com.foodcourt.traceabilitymanagement.domain.model.order.OrderModel;
import com.foodcourt.traceabilitymanagement.domain.model.order.OrderUpdateModel;

import java.util.List;

public interface IOrderRestPort {

    OrderUpdateModel findOrderById(Long orderId);
    List<OrderModel> findAllOrdersByEmployeeId(Long employeeId);
    List<OrderModel> findAllOrdersByRestaurantId(Long restaurantId);
}
