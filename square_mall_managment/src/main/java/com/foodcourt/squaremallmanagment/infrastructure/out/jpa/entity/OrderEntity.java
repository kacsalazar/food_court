package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity;

import java.util.Date;

public class OrderEntity {

    private Long id;
    private Long idClient;
    private Date orderDate;
    private String status;
    private Long idChef;
    private Long idRestaurant;
}
