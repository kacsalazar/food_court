package com.foodcourt.squaremallmanagment.infrastructure.out.restclient;

import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;
import com.foodcourt.squaremallmanagment.domain.spi.ITraceabilityRestPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
@Slf4j
public class TraceabilityRestAdapter implements ITraceabilityRestPort {

    private final RestTemplate restTemplate;
    private final String USER_SERVICE_URL = "http://localhost:8083/api/v1/traceability/";

    public void saveTraceability(TraceabilityModel model) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TraceabilityModel> request = new HttpEntity<>(model, headers);

        // Llama al endpoint POST del otro microservicio
        ResponseEntity<Void> response = restTemplate.exchange(
                USER_SERVICE_URL + "save", HttpMethod.POST,
                request,
                Void.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Traza enviada correctamente.");
        } else {
            System.out.println("Error al enviar traza: " + response.getStatusCode());
        }
    }

}
