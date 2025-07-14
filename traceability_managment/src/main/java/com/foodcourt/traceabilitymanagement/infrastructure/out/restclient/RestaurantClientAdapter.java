package com.foodcourt.traceabilitymanagement.infrastructure.out.restclient;

import com.foodcourt.traceabilitymanagement.domain.model.restaurant.RestaurantModel;
import com.foodcourt.traceabilitymanagement.domain.spi.IRestaurantRestPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
@Slf4j
public class RestaurantClientAdapter implements IRestaurantRestPort {

    private final RestTemplate restTemplate;
    private final String USER_SERVICE_URL = "http://localhost:8082/api/v1/restaurant/";

    @Override
    public RestaurantModel findRestaurantById(Long restaurantId) {
        String url = USER_SERVICE_URL + "restaurant/" + restaurantId;
        log.info("Checking if owner exists with DNI: {}", restTemplate.getForObject(url, RestaurantModel.class));
        return restTemplate.getForObject(url, RestaurantModel.class);
    }
}
