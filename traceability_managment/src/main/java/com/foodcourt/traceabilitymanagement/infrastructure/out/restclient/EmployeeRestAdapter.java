package com.foodcourt.traceabilitymanagement.infrastructure.out.restclient;

import com.foodcourt.traceabilitymanagement.domain.model.user.EmployeeModel;
import com.foodcourt.traceabilitymanagement.domain.spi.IEmployeeRestPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class EmployeeRestAdapter implements IEmployeeRestPort {

    private final RestTemplate restTemplate;
    private final String USER_SERVICE_URL = "http://localhost:8081/api/v1/user/";

    @Override
    public List<EmployeeModel> getEmployeesByRestaurantId(Long restaurantId) {
        String url = USER_SERVICE_URL + "employees/" + restaurantId;
        log.info("Fetching employees for restaurant ID: {}", restaurantId);
        EmployeeModel[] employees = restTemplate.getForObject(url, EmployeeModel[].class);
        return List.of(employees);
    }
}
