package com.foodcourt.squaremallmanagment.infrastructure.out.restclient;

import com.foodcourt.squaremallmanagment.domain.model.EmployeeModel;
import com.foodcourt.squaremallmanagment.domain.spi.IEmployeeRestPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
@Slf4j
public class EmployeeRestAdapter implements IEmployeeRestPort {


    private final RestTemplate restTemplate;
    private final String USER_SERVICE_URL = "http://localhost:8081/api/v1/user/";

    public EmployeeModel getEmployeeByDni(String dni) {
        String url = USER_SERVICE_URL + "dni/" + dni;
        log.info("Checking if owner exists with DNI: {}", restTemplate.getForObject(url, EmployeeModel.class));
        return restTemplate.getForObject(url, EmployeeModel.class);
    }

}
