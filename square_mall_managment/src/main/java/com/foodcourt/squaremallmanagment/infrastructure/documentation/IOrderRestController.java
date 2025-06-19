package com.foodcourt.squaremallmanagment.infrastructure.documentation;
import com.foodcourt.squaremallmanagment.application.dto.request.OrderCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import static com.foodcourt.squaremallmanagment.infrastructure.documentation.ApiResponsesOKExample.SAVE_RESTAURANT_EXAMPLE;

public interface IOrderRestController {


    @Operation(
            summary = "Create a new Order",
            description = "Saves a new order using the provided request data.",
            tags = {"Order Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Order created successfully",
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
    ResponseEntity<Void> makeOrder(
            @RequestBody(
                    description = "Order data to be saved",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderCreateRequest.class),
                            examples = @ExampleObject(
                                    name = "Order Request Dto Example",
                                    value = SAVE_RESTAURANT_EXAMPLE)
                    )
            )
            OrderCreateRequest orderCreateRequest
    );

}
