package com.foodcourt.squaremallmanagment.infrastructure.input.rest;

import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestDto;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponseDto;
import com.foodcourt.squaremallmanagment.application.handler.IDishHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/dish")
public class DishRestController {

    private final IDishHandler dishHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveDish(@RequestBody DishRequestDto dishRequestDto) {
        dishHandler.saveDish(dishRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DishResponseDto> updateDish(@PathVariable Long id, @RequestBody DishRequestUpdateDto dishRequestUpdateDto) {
        return ResponseEntity.ok(dishHandler.updateDish(id, dishRequestUpdateDto));
    }
}
