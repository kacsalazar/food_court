package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "orders")
@Entity
public class OrderEntity {

    @Id
    @Column(nullable = false)
    private Long id;
    @Column(name = "id_client")
    private Long idClient;
    @Column(name = "order_date")
    private Date orderDate;
    private String status;
    @Column(name = "id_chef")
    private Long idChef;
    @Column(name = "id_restaurant")
    private Long idRestaurant;
}
