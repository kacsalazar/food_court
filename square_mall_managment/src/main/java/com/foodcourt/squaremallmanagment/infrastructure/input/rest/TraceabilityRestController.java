package com.foodcourt.squaremallmanagment.infrastructure.input.rest;

import com.foodcourt.squaremallmanagment.application.dto.response.EmployeeRankingResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.TraceabilityResponse;
import com.foodcourt.squaremallmanagment.application.handler.ITraceabilityHandler;
import com.foodcourt.squaremallmanagment.domain.model.EmployeeRankingModel;
import com.foodcourt.squaremallmanagment.infrastructure.documentation.ITraceabilityRestController;
import com.foodcourt.squaremallmanagment.infrastructure.input.rest.util.RolesEnum;
import com.foodcourt.squaremallmanagment.infrastructure.input.rest.util.SecurityExpressions;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/traceability")
public class TraceabilityRestController implements ITraceabilityRestController {

    private final ITraceabilityHandler traceabilityHandler;

    @PreAuthorize(SecurityExpressions.CUSTOMER)
    @GetMapping("/{orderId}")
    public ResponseEntity<List<TraceabilityResponse>> getTraceabilityByOrderId(@PathVariable Long orderId) {
        List<TraceabilityResponse> traceabilityResponses = traceabilityHandler.findAllTracesByOrderId(orderId);
        return ResponseEntity.ok(traceabilityResponses);
    }

    @PreAuthorize(SecurityExpressions.OWNER)
    @GetMapping("/time/{orderId}")
    public ResponseEntity<String> getOrderProcessingTime(@PathVariable Long orderId) {
        String processingTime = traceabilityHandler.getOrderProcessingTime(orderId);
        return ResponseEntity.ok(processingTime);
    }

    @PreAuthorize(SecurityExpressions.OWNER)
    @GetMapping("/ranking/{orderId}")
    public ResponseEntity<List<EmployeeRankingResponse>> getRankingForOrderByEmployeeId(@PathVariable Long orderId) {
        List<EmployeeRankingResponse> employeeRankingResponses = traceabilityHandler.getRankingForOrderByEmployeeId(orderId);
        return ResponseEntity.ok(employeeRankingResponses);
    }

}
