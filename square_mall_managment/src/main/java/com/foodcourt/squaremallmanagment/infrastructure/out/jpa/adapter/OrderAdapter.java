package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.squaremallmanagment.domain.model.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.OrderModelReturn;
import com.foodcourt.squaremallmanagment.domain.spi.IOrderPersistencePort;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.OrderVsDishEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.OrderEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.impl.OrderEntityMapper;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.IOrderVsDishRepository;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.IOrderRepository;
import com.foodcourt.squaremallmanagment.infrastructure.out.restclient.UserClientAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderAdapter implements IOrderPersistencePort {

    private final UserClientAdapter userClientAdapter;
    private final IOrderRepository orderRepository;
    private final IOrderVsDishRepository orderDishRepository;

    @Override
    public void makeOrder(OrderModel orderModel) {

        Long persistedOrderId = saveOrder(orderModel);
        relateDishToOrder(orderModel, persistedOrderId);
    }

    private Long saveOrder(OrderModel orderModel) {
        OrderEntity order = OrderEntityMapper.toOrderEntity(orderModel);
        order.setStatus("PENDING");
        order.setIdClient(userClientAdapter.ownerExists(orderModel.getUserDni()).getId());
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


}
