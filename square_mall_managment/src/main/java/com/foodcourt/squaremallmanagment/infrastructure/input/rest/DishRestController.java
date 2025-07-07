package com.foodcourt.squaremallmanagment.infrastructure.input.rest;

import com.foodcourt.squaremallmanagment.application.dto.request.DishCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.request.DishStatusRequest;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.DishRestaurantResponse;
import com.foodcourt.squaremallmanagment.application.handler.IDishHandler;
import com.foodcourt.squaremallmanagment.infrastructure.documentation.IDishRestController;
import com.foodcourt.squaremallmanagment.infrastructure.input.rest.util.SecurityExpressions;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/dish")
public class DishRestController implements IDishRestController {

    private final IDishHandler dishHandler;

    @PreAuthorize(SecurityExpressions.OWNER)
    @PostMapping("/")
    public ResponseEntity<Void> saveDish(@RequestBody DishCreateRequest dishCreateRequest) {
        dishHandler.saveDish(dishCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize(SecurityExpressions.OWNER)
    @PatchMapping("/{dishId}")
    public ResponseEntity<DishResponse> updateDish(@PathVariable Long dishId, @RequestBody DishRequestUpdateDto dishRequestUpdateDto) {
        return ResponseEntity.ok(dishHandler.updateDish(dishId, dishRequestUpdateDto));
    }

    @PreAuthorize(SecurityExpressions.OWNER)
    @PatchMapping("/{dishId}/status")
    public ResponseEntity<DishResponse> disableDish(@PathVariable Long dishId, @RequestBody DishStatusRequest status) {
        return ResponseEntity.ok(dishHandler.disableDish(dishId, status.getStatus()));
    }

    @PreAuthorize(SecurityExpressions.CUSTOMER)
    @GetMapping("/{idRestaurant}/dishes")
    public ResponseEntity<List<DishRestaurantResponse>> getDishesByCategory(@RequestParam (required = false) Long idCategory, @PathVariable Long idRestaurant,
                                                                            @RequestParam(defaultValue = "0") Integer page,
                                                                            @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(dishHandler.getDishesByCategory(idRestaurant, idCategory, page, size));
    }
}
