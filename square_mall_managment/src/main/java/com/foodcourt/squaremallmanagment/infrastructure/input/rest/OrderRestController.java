package com.foodcourt.squaremallmanagment.infrastructure.input.rest;

import com.foodcourt.squaremallmanagment.application.dto.request.OrderCreateRequest;
import com.foodcourt.squaremallmanagment.application.handler.IOrderHandler;
import com.foodcourt.squaremallmanagment.infrastructure.documentation.IOrderRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

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
}
