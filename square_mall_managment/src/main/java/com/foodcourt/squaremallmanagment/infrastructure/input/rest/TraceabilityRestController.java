package com.foodcourt.squaremallmanagment.infrastructure.input.rest;

import com.foodcourt.squaremallmanagment.application.dto.response.TraceabilityResponse;
import com.foodcourt.squaremallmanagment.application.handler.ITraceabilityHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/traceability")
public class TraceabilityRestController {

    private final ITraceabilityHandler traceabilityHandler;

    @GetMapping("/{orderId}")
    public ResponseEntity<List<TraceabilityResponse>> getTraceabilityByOrderId(@PathVariable Long orderId) {
        List<TraceabilityResponse> traceabilityResponses = traceabilityHandler.findAllTracesByOrderId(orderId);
        return ResponseEntity.ok(traceabilityResponses);
    }

}
