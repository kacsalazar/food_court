package com.foodcourt.squaremallmanagment.infrastructure.documentation;

import com.foodcourt.squaremallmanagment.application.dto.request.RestaurantRequestDto;
import com.foodcourt.squaremallmanagment.application.dto.response.RestaurantResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
            @RequestBody(
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
            RestaurantRequestDto restaurantRequestDto
    );

    @Operation(
            summary = "Get all restaurants with pagination",
            description = "Returns a paginated list of all registered restaurants.",
            tags = {"Restaurant Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of restaurants returned successfully",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RestaurantResponse.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    ResponseEntity<List<RestaurantResponse>> getAllRestaurants(
            @Parameter(
                    name = "page",
                    description = "Page number (starts from 0)",
                    example = "0",
                    required = false,
                    schema = @Schema(type = "integer", defaultValue = "0")
            )
            @RequestParam(defaultValue = "0") Integer page,

            @Parameter(
                    name = "size",
                    description = "Number of records per page",
                    example = "10",
                    required = false,
                    schema = @Schema(type = "integer", defaultValue = "10")
            )
            @RequestParam(defaultValue = "10") Integer size
    );
}
