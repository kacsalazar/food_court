package com.foodcourt.squaremallmanagment.infrastructure.configuration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
public class UserClient {

    private final RestTemplate restTemplate;
    private final String USER_SERVICE_URL = "http://localhost:8081/api/v1/user/";

    public Boolean verifyUserRol(Long id, String role) {
        String url = USER_SERVICE_URL + "verify/" + id + "/" + role;
        return restTemplate.getForObject(url, Boolean.class);
    }



}
