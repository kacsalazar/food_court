package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModelReturn;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderUpdateModel;
import com.foodcourt.squaremallmanagment.domain.spi.IOrderPersistencePort;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.OrderVsDishEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.OrderEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.impl.OrderEntityMapper;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.IOrderVsDishRepository;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final IOrderVsDishRepository orderDishRepository;

    @Override
    public void makeOrder(OrderModel orderModel, Long userId) {

        Long persistedOrderId = saveOrder(orderModel, userId);
        relateDishToOrder(orderModel, persistedOrderId);
    }

    private Long saveOrder(OrderModel orderModel, Long userId) {
        OrderEntity order = OrderEntityMapper.toOrderEntity(orderModel);
        order.setIdClient(userId);
        return orderRepository.save(order).getId();
    }

    private void relateDishToOrder(OrderModel orderModel, Long orderId) {
        for (OrderModel.Dish dish : orderModel.getDishes()) {
            OrderVsDishEntity orderDish = OrderEntityMapper.toOrderDishEntity(dish, orderId);
            orderDishRepository.save(orderDish);
        }
    }

    public List<OrderModelReturn> findOrdersByIdUser(Long id) {
        return OrderEntityMapper.toOrderModelReturn(orderRepository.findOrdersByIdClient(id));
    }

    @Override
    public OrderUpdateModel findOrderById(Long orderId) {
        return OrderEntityMapper.toOrderUpdateModel(orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId)));
    }

    @Override
    public void updateOrder(OrderUpdateModel orderModel) {
        OrderEntity entity = OrderEntityMapper.toOrderEntityUpdate(orderModel);
        orderRepository.save(entity);

    }

    @Override
    public List<OrderModel> getOrdersByEmployee(String status, Integer page, Integer size, Long idEmployee) {
        List<OrderEntity> orders = orderRepository.findOrdersByStatus(idEmployee, status, size, page);
        return orders.stream().map(order -> OrderEntityMapper.toOrderModel(order,
                        orderDishRepository.findByOrderId(order.getId())))
                .toList();
    }

    public List<OrderModel> findAllOrdersByEmployeeId(Long employeeId) {
        List<OrderEntity> orders = orderRepository.findAllOrdersByEmployeeId(employeeId);
        return orders.stream()
                .map(order -> OrderEntityMapper.toOrderModel(order, orderDishRepository.findByOrderId(order.getId())))
                .toList();
    }

    @Override
    public List<OrderModel> findAllOrdersByRestaurantId(Long restaurantId) {
        List<OrderEntity> orders = orderRepository.findAllOrdersByRestaurantId(restaurantId);
        log.info(orders + "ORDENES");
        return orders.stream()
                .map(order -> OrderEntityMapper.toOrderModel(order, orderDishRepository.findByOrderId(order.getId())))
                .toList();
    }
}
