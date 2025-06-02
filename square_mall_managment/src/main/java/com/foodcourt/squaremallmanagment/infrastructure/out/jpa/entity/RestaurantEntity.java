package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Column;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "roles")
@Entity
public class RestaurantEntity {

    @Id
    @Column(nullable = false)
    private Long id;
    @Column(length = 50)
    private String name;
    private String address;
    private Long id_owner;
    private String phoneNumber;
    private String urlLogo;
    private String nit;
}
