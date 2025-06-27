package com.foodcourt.squaremallmanagment;

import com.foodcourt.squaremallmanagment.application.dto.request.DishCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.request.RestaurantRequestDto;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponse;
import com.foodcourt.squaremallmanagment.domain.model.UserModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishUpdateModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.ListDishesByRestaurantModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModelReturn;
import com.foodcourt.squaremallmanagment.domain.model.restaurant.RestaurantModel;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.DishEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.OrderEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.RestaurantEntity;

import java.time.LocalDate;
import java.util.List;

public class CreatorMocks {

    public static RestaurantRequestDto createRestaurantRequestDto() {
        return RestaurantRequestDto.builder()
                .name("Pizza Place")
                .address("123 Main St")
                .idOwner(1L)
                .phoneNumber("555-1234")
                .urlLogo("http://example.com/logo.png")
                .nit("NIT123456").build();
    }

    public static RestaurantModel createRestaurantModel() {
        return RestaurantModel.builder()
                .id(2L)
                .name("Pizza Place")
                .address("123 Main St")
                .idOwner(1L)
                .phoneNumber("5551234")
                .urlLogo("http://example.com/logo.png")
                .nit("123456").build();
    }

    public static RestaurantEntity createRestaurantEntity() {
        return RestaurantEntity.builder()
                .id(1L)
                .name("Pizza Place")
                .address("123 Main St")
                .idOwner(1L)
                .phoneNumber("555-1234")
                .urlLogo("http://example.com/logo.png")
                .nit("NIT123456").build();
    }

    public static DishEntity createDishEntity(){
        return DishEntity.builder()
                .name("Pizza")
                .idCategory(1L)
                .description("Delicious pizza")
                .price(10.0)
                .idRestaurant(2L)
                .imageUrl("http://example.com/pizza.png")
                .isActive(true).build();
    }

    public static List<ListDishesByRestaurantModel> createListDishesByRestaurantModel() {
        return List.of(
                ListDishesByRestaurantModel.builder()
                        .name("Pizza")
                        .description("Delicious pizza")
                        .imageUrl("http://example.com/pizza.png")
                        .build(),
                ListDishesByRestaurantModel.builder()
                        .name("Burger")
                        .description("Juicy burger")
                        .imageUrl("http://example.com/burger.png")
                        .build()
        );
    }

    public static List<DishEntity> entities() {
        return List.of(
                DishEntity.builder()
                        .name("Pizza")
                        .idCategory(1L)
                        .description("Delicious pizza")
                        .price(10.0)
                        .idRestaurant(2L)
                        .imageUrl("http://example.com/pizza.png")
                        .isActive(true).build(),
                DishEntity.builder()
                        .name("Burger")
                        .idCategory(2L)
                        .description("Juicy burger")
                        .price(8.0)
                        .idRestaurant(2L)
                        .imageUrl("http://example.com/burger.png")
                        .isActive(true).build()
        );
    }

    public static DishCreateRequest createDishRequestDto() {
        return DishCreateRequest.builder()
                .name("Pizza")
                .idCategory(1L)
                .description("Delicious pizza")
                .price(10.0)
                .idRestaurant(2L)
                .imageUrl("http://example.com/pizza.png")
                .build();
    }

    public static DishRequestUpdateDto createDishRequestUpdateDto() {
        return DishRequestUpdateDto.builder()
                .description("Updated delicious pizza")
                .price(12.0)
                .build();
    }

    public static DishResponse createDishResponseDto() {
        return DishResponse.builder()
                .name("Pizza")
                .idCategory(1L)
                .description("Delicious pizza")
                .price(10.0)
                .idRestaurant(2L)
                .imageUrl("http://example.com/pizza.png")
                .build();

    }

    public static DishUpdateModel createDishUpdateModel() {
        return DishUpdateModel.builder()
                .description("Updated delicious pizza")
                .price(12.0)
                .build();
    }

    public static DishModel createDishModel(){
        return DishModel.builder()
                .dishInfo(
                        DishModel.DishInfo.builder()
                                .name("Pizza")
                                .idCategory(1L)
                                .description("Delicious pizza")
                                .price(10.0)
                                .imageUrl("http://example.com/pizza.png")
                                .isActive(true)
                                .build()
                )
                .restaurantInfo(
                        DishModel.RestaurantInfo.builder()
                                .idRestaurant(2L)
                                .build()
                ).build();
    }
    public static UserModel createUserModel() {
        return UserModel.builder()
                .id(1L)
                .dni("123456789")
                .name("John Doe")
                .phoneNumber("555-1234")
                .build();
    }

    public static List<RestaurantEntity> createRestaurantEntityList() {
        return List.of(
                RestaurantEntity.builder()
                        .id(1L)
                        .name("Pizza Place")
                        .address("123 Main St")
                        .idOwner(1L)
                        .phoneNumber("555-1234")
                        .urlLogo("http://example.com/logo.png")
                        .nit("NIT123456").build(),
                RestaurantEntity.builder()
                        .id(2L)
                        .name("Burger Joint")
                        .address("456 Elm St")
                        .idOwner(2L)
                        .phoneNumber("555-5678")
                        .urlLogo("http://example.com/burger.png")
                        .nit("NIT654321").build()
        );


    }

    public static List<OrderEntity> orderEntities() {
        return List.of(
                OrderEntity.builder()
                        .id(1L)
                        .idRestaurant(1L)
                        .idChef(1L)
                        .status("PENDING")
                        .idClient(1L)
                        .securityPin("1234")
                        .orderDate(java.sql.Date.valueOf(LocalDate.of(2023, 10, 1)))
                        .build(),
                OrderEntity.builder()
                        .id(2L)
                        .idRestaurant(2L)
                        .idChef(2L)
                        .status("COMPLETED")
                        .idClient(1L)
                        .orderDate(java.sql.Date.valueOf(LocalDate.of(2023, 10, 1)))
                        .securityPin("5678")
                        .build()
        );
    }

    public static List<OrderModelReturn> ordersModelReturn() {
        return List.of(
                OrderModelReturn.builder()
                        .orderDate(LocalDate.of(2023, 10, 1))
                        .status("PENDING")
                        .restaurantId(1L)
                        .employeeId(1L)
                        .build(),
                OrderModelReturn.builder()
                        .orderDate(LocalDate.of(2023, 10, 1))
                        .status("COMPLETED")
                        .restaurantId(2L)
                        .employeeId(2L)
                        .build()
        );
    }
}
