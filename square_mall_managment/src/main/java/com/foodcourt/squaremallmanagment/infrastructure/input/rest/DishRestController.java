package com.foodcourt.squaremallmanagment.infrastructure.input.rest;

import com.foodcourt.squaremallmanagment.application.dto.request.DishCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.request.DishStatusRequest;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponseDto;
import com.foodcourt.squaremallmanagment.application.handler.IDishHandler;
import com.foodcourt.squaremallmanagment.infrastructure.documentation.IDishRestController;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/dish")
public class DishRestController implements IDishRestController {

    private final IDishHandler dishHandler;

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/")
    public ResponseEntity<Void> saveDish(@RequestBody DishCreateRequest dishCreateRequest) {
        dishHandler.saveDish(dishCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('OWNER')")
    @PatchMapping("/{id}")
    public ResponseEntity<DishResponseDto> updateDish(@PathVariable Long id, @RequestBody DishRequestUpdateDto dishRequestUpdateDto) {
        return ResponseEntity.ok(dishHandler.updateDish(id, dishRequestUpdateDto));
    }

    @PreAuthorize("hasRole('OWNER')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<DishResponseDto> disableDish(@PathVariable Long id, @RequestBody DishStatusRequest status) {
        // This method is not implemented in the original code, but you can add logic here if needed.
        return ResponseEntity.ok(dishHandler.disableDish(id, status.getStatus()));
    }
}
