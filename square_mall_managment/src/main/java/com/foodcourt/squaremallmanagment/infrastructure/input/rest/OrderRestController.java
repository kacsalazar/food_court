package com.foodcourt.squaremallmanagment.infrastructure.input.rest;

import com.foodcourt.squaremallmanagment.application.dto.request.DeliverOrderRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.NotificationRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.OrderCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.response.OrderResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.OrderUpdateResponse;
import com.foodcourt.squaremallmanagment.application.handler.IOrderHandler;
import com.foodcourt.squaremallmanagment.infrastructure.documentation.IOrderRestController;
import com.foodcourt.squaremallmanagment.infrastructure.input.rest.util.SecurityExpressions;
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

    @PreAuthorize(SecurityExpressions.CUSTOMER)
    @PostMapping("/")
    public ResponseEntity<Void> makeOrder(@RequestBody OrderCreateRequest orderCreateRequest) {
        orderHandler.makeOrder(orderCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize(SecurityExpressions.EMPLOYEE)
    @PatchMapping("/assign/{orderId}")
    public ResponseEntity<Void> assignOrderToEmployee(@PathVariable Long orderId){
        orderHandler.assignOrderToEmployee(orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize(SecurityExpressions.EMPLOYEE)
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getOrdersByEmployee(@RequestParam String status,
                                                                   @RequestParam(defaultValue = "0") Integer page,
                                                                   @RequestParam(defaultValue = "10") Integer size) {

        return ResponseEntity.ok(orderHandler.getOrdersByEmployee(status, page, size));
    }

    @PreAuthorize(SecurityExpressions.EMPLOYEE)
    @PatchMapping("/ready/{orderId}")
    public ResponseEntity<Void> changeOrderToReady(@PathVariable Long orderId,
                                                        @RequestBody NotificationRequest notification) {
        orderHandler.changeOrderToReady(notification, orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize(SecurityExpressions.EMPLOYEE)
    @PatchMapping("/deliver/{orderId}")
    public ResponseEntity<Void> deliverOrder(@PathVariable Long orderId,
                                                   @RequestBody DeliverOrderRequest deliverOrder) {
        orderHandler.deliverOrder(deliverOrder, orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize(SecurityExpressions.CUSTOMER)
    @PatchMapping("/cancel/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        orderHandler.cancelOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderUpdateResponse> findOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderHandler.findOrderById(orderId));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<OrderResponse>> findAllOrdersByEmployeeId (Long employeeId){
        return ResponseEntity.ok(orderHandler.findAllOrdersByEmployeeId(employeeId));
    }
}
