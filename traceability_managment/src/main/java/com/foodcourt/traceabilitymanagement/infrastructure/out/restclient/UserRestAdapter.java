package com.foodcourt.traceabilitymanagement.infrastructure.out.restclient;

import com.foodcourt.traceabilitymanagement.domain.model.user.UserModel;
import com.foodcourt.traceabilitymanagement.domain.spi.IUserRestPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
@Slf4j
public class UserRestAdapter implements IUserRestPort {

    private final RestTemplate restTemplate;
    private final String USER_SERVICE_URL = "http://localhost:8081/api/v1/user/";

    @Override
    public UserModel ownerExists(String dni) {
        String url = USER_SERVICE_URL + "dni/" + dni;
        log.info("Checking if owner exists with DNI: {}", restTemplate.getForObject(url, UserModel.class));
        return restTemplate.getForObject(url, UserModel.class);
    }
}
