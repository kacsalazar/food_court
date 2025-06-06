package com.foodcourt.squaremallmanagment.infrastructure.documentation;

import com.foodcourt.squaremallmanagment.application.dto.request.RestaurantRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import static com.foodcourt.squaremallmanagment.infrastructure.documentation.ApiResponsesOKExample.SAVE_RESTAURANT_EXAMPLE;

public interface IRestaurantRestController {

    @Operation(
            summary = "Create a new restaurant",
            description = "Saves a new restaurant using the provided request data.",
            tags = {"Restaurant Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Restaurant created successfully",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid restaurant data provided",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    ResponseEntity<Void> saveRestaurant(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Restaurant data to be saved",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RestaurantRequestDto.class),
                            examples = @ExampleObject(
                                    name = "Restaurant Request Dto Example",
                                    value = SAVE_RESTAURANT_EXAMPLE)
                    )
            )
            @RequestBody RestaurantRequestDto restaurantRequestDto
    );
}
