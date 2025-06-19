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
@Table(name = "restaurants")
@Entity
public class RestaurantEntity {

    @Id
    @Column(nullable = false)
    private Long id;
    @Column(length = 50)
    private String name;
    private String address;
    @Column(name = "id_owner")
    private Long idOwner;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "url_logo")
    private String urlLogo;
    private String nit;
}
