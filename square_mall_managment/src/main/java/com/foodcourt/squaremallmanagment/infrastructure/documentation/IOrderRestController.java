package com.foodcourt.squaremallmanagment.infrastructure.documentation;
import com.foodcourt.squaremallmanagment.application.dto.request.DeliverOrderRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.NotificationRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.OrderCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.response.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @Operation(
            summary = "Assign an order to the employee",
            description = "Assigns the specified order to the authenticated employee. Only accessible to users with role EMPLOYEE.",
            tags = {"Order Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order assigned to employee successfully",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - User does not have EMPLOYEE role",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    ResponseEntity<Void> assignOrderToEmployee(
            @Parameter(
                    name = "orderId",
                    description = "ID of the order to assign",
                    required = true,
                    example = "1001",
                    schema = @Schema(type = "integer")
            )
            @PathVariable Long orderId
    );

    @Operation(
            summary = "Get orders assigned to employee by status",
            description = "Returns a paginated list of orders assigned to the authenticated employee, filtered by status. Only accessible to users with role EMPLOYEE.",
            tags = {"Order Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of orders returned successfully",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderResponse.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - User does not have EMPLOYEE role",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    ResponseEntity<List<OrderResponse>> getOrdersByEmployee(
            @Parameter(
                    name = "status",
                    description = "Status of the orders to retrieve (e.g., PENDING, READY, DELIVERED)",
                    required = true,
                    example = "PENDING",
                    schema = @Schema(type = "string")
            )
            @RequestParam String status,

            @Parameter(
                    name = "page",
                    description = "Page number (starts from 0)",
                    required = false,
                    example = "0",
                    schema = @Schema(type = "integer", defaultValue = "0")
            )
            @RequestParam(defaultValue = "0") Integer page,

            @Parameter(
                    name = "size",
                    description = "Number of records per page",
                    required = false,
                    example = "10",
                    schema = @Schema(type = "integer", defaultValue = "10")
            )
            @RequestParam(defaultValue = "10") Integer size
    );

    @Operation(
            summary = "Change order status to READY",
            description = "Changes the status of an order to READY and sends a notification. Only accessible to users with role EMPLOYEE.",
            tags = {"Order Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order status changed to READY successfully",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - User does not have EMPLOYEE role",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    ResponseEntity<Void> changeOrderToReady(
            @Parameter(
                    name = "orderId",
                    description = "ID of the order to update",
                    required = true,
                    example = "1234",
                    schema = @Schema(type = "integer")
            )
            @PathVariable Long orderId,

            @RequestBody(
                    description = "Notification data to be sent when order is marked as READY",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotificationRequest.class)
                    )
            )
            NotificationRequest notification
    );
    @Operation(
            summary = "Mark order as DELIVERED",
            description = "Changes the status of an order to DELIVERED using the provided delivery details. Only accessible to users with role EMPLOYEE.",
            tags = {"Order Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order marked as DELIVERED successfully",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - User does not have EMPLOYEE role",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    ResponseEntity<Void> deliverOrder(
            @Parameter(
                    name = "orderId",
                    description = "ID of the order to deliver",
                    required = true,
                    example = "1234",
                    schema = @Schema(type = "integer")
            )
            @PathVariable Long orderId,

            @RequestBody(
                    description = "Delivery details for completing the order",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DeliverOrderRequest.class)
                    )
            )
            DeliverOrderRequest deliverOrder
    );

    @Operation(
            summary = "Cancel an order",
            description = "Cancels the specified order. Only accessible to users with the CUSTOMER role.",
            tags = {"Order Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order cancelled successfully",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - User does not have CUSTOMER role or is not authorized to cancel this order",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    ResponseEntity<Void> cancelOrder(
            @Parameter(
                    name = "orderId",
                    description = "ID of the order to cancel",
                    required = true,
                    example = "1234",
                    schema = @Schema(type = "integer")
            )
            @PathVariable Long orderId
    );
}
