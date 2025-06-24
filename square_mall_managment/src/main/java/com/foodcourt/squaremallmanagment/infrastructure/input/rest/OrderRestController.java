package com.foodcourt.squaremallmanagment.infrastructure.input.rest;

import com.foodcourt.squaremallmanagment.application.dto.request.OrderCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.response.OrderResponse;
import com.foodcourt.squaremallmanagment.application.handler.IOrderHandler;
import com.foodcourt.squaremallmanagment.infrastructure.documentation.IOrderRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderRestController implements IOrderRestController {

    private final IOrderHandler orderHandler;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/")
    public ResponseEntity<Void> makeOrder(@RequestBody OrderCreateRequest orderCreateRequest) {
        orderHandler.makeOrder(orderCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PatchMapping("/assign/{orderId}")
    public ResponseEntity<Void> assignOrderToEmployee(@PathVariable Long orderId){
        orderHandler.assignOrderToEmployee(orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getOrdersByEmployee(@RequestParam String status,
                                                                   @RequestParam(defaultValue = "0") Integer page,
                                                                   @RequestParam(defaultValue = "10") Integer size) {

        return ResponseEntity.ok(orderHandler.getOrdersByEmployee(status, page, size));
    }
}
