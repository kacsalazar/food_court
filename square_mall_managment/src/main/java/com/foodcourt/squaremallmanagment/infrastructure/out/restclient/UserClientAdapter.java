package com.foodcourt.squaremallmanagment.infrastructure.out.restclient;

import com.foodcourt.squaremallmanagment.domain.spi.IUserClientPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
@Slf4j
public class UserClientAdapter implements IUserClientPort {

    private final RestTemplate restTemplate;
    private final String USER_SERVICE_URL = "http://localhost:8081/api/v1/user/";

    public Boolean isValidUser(String dni, String role) {
        String url = USER_SERVICE_URL + "verify/" + dni + "/" + role;
        return restTemplate.getForObject(url, Boolean.class);
    }
}
