package com.foodcourt.squaremallmanagment.domain.spi;

import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModelReturn;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderUpdateModel;

import java.util.List;

public interface IOrderPersistencePort {
    void makeOrder(OrderModel orderModel, Long userId);
    List<OrderModelReturn> findOrdersByIdUser(Long id);
    OrderUpdateModel findOrderById(Long orderId);
    void updateOrder(OrderUpdateModel orderModel);
    List<OrderModel> getOrdersByEmployee(String status, Integer page, Integer size, Long idEmployee);
    List<OrderModel> findAllOrdersByEmployeeId(Long employeeId);

}
