package com.foodcourt.usersmanagment.infrastructure.out.restclient;

import com.foodcourt.usersmanagment.domain.model.RestaurantModel;
import com.foodcourt.usersmanagment.domain.spi.IRestaurantClientPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
@Slf4j
public class RestaurantClientAdapter implements IRestaurantClientPort {

    private final RestTemplate restTemplate;
    private final String USER_SERVICE_URL = "http://localhost:8082/api/v1/restaurant/";

    public RestaurantModel getRestaurantIdByOwner(Long ownerId) {
        String url = USER_SERVICE_URL + "ownerId/" + ownerId;
        log.info("Checking if owner exists with DNI: {}", restTemplate.getForObject(url, RestaurantModel.class));
        return restTemplate.getForObject(url, RestaurantModel.class);
    }
}
