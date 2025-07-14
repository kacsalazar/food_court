package com.foodcourt.traceabilitymanagement.infrastructure.input.rest;

import com.foodcourt.traceabilitymanagement.application.dto.request.TraceabilityRequest;
import com.foodcourt.traceabilitymanagement.application.dto.response.DurationTimeResponse;
import com.foodcourt.traceabilitymanagement.application.dto.response.EmployeeRankingResponse;
import com.foodcourt.traceabilitymanagement.application.dto.response.TraceabilityResponse;
import com.foodcourt.traceabilitymanagement.application.handler.ITraceabilityHandler;
import com.foodcourt.traceabilitymanagement.infrastructure.documentation.ITraceabilityRestController;
import com.foodcourt.traceabilitymanagement.infrastructure.input.rest.util.SecurityExpressions;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/time/{restaurantId}")
    public ResponseEntity<List<DurationTimeResponse>> getOrdersProcessingTime(@PathVariable Long restaurantId) {
        List<DurationTimeResponse> processingTime = traceabilityHandler.getOrdersProcessingTime(restaurantId);
        return ResponseEntity.ok(processingTime);
    }

    @PreAuthorize(SecurityExpressions.OWNER)
    @GetMapping("/ranking/{restaurantId}")
    public ResponseEntity<List<EmployeeRankingResponse>> getRankingForOrderByEmployeeId(@PathVariable Long restaurantId) {
        List<EmployeeRankingResponse> employeeRankingResponses = traceabilityHandler.getRankingForOrderByEmployeeId(restaurantId);
        return ResponseEntity.ok(employeeRankingResponses);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveTraceability(@RequestBody TraceabilityRequest traceabilityRequest){
        traceabilityHandler.saveTraceability(traceabilityRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
