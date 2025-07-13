package com.foodcourt.traceabilitymanagement.infrastructure.out.restclient;

import com.foodcourt.traceabilitymanagement.domain.model.order.OrderModel;
import com.foodcourt.traceabilitymanagement.domain.model.order.OrderUpdateModel;
import com.foodcourt.traceabilitymanagement.domain.spi.IOrderRestPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class OrderRestAdapter implements IOrderRestPort {

    private final RestTemplate restTemplate;
    private final String USER_SERVICE_URL = "http://localhost:8082/api/v1/order/";



    @Override
    public OrderUpdateModel findOrderById(Long orderId) {
        String url = USER_SERVICE_URL + "order/" + orderId;
        log.info("Checking if owner exists with DNI: {}", restTemplate.getForObject(url, OrderUpdateModel.class));
        return restTemplate.getForObject(url, OrderUpdateModel.class);
    }

    @Override
    public List<OrderModel> findAllOrdersByEmployeeId(Long employeeId) {
        String url = USER_SERVICE_URL + "employee/" + employeeId;
        OrderModel[] orders = restTemplate.getForObject(url, OrderModel[].class);
        return List.of(orders);
    }

    @Override
    public List<OrderModel> findAllOrdersByRestaurantId(Long restaurantId) {
        String url = USER_SERVICE_URL + "restaurant/" + restaurantId;
        OrderModel[] orders = restTemplate.getForObject(url, OrderModel[].class);
        return List.of(orders);
    }
}
