package com.foodcourt.traceabilitymanagement.infrastructure.exceptionhandler;

import com.foodcourt.traceabilitymanagement.domain.exception.*;
import com.foodcourt.traceabilitymanagement.infrastructure.exception.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(NotPermissionException.class)
    public ResponseEntity<Map<String, String>> handleNotPermissionException(
            NotPermissionException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(MESSAGE, ConstantException.NOT_PERMISSION.getMessage()));
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantNotFoundException(
            RestaurantNotFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(MESSAGE, ConstantException.RESTAURANT_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(TraceabilityEmptyException.class)
    public ResponseEntity<Map<String, String>> handleTraceabilityEmptyException(
            TraceabilityEmptyException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Collections.singletonMap(MESSAGE, ConstantException.TRACEABILITY_EMPTY.getMessage()));
    }

    @ExceptionHandler(EmployeesNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEmployeesNotFoundException(
            EmployeesNotFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ConstantException.EMPLOYEES_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(OrdersNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleOrdersNotFoundException(
            OrdersNotFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ConstantException.ORDERS_NOT_FOUND.getMessage()));
    }
}