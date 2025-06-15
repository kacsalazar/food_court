package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "dishes")
@Entity
public class DishEntity {

    @Id
    @Column(nullable = false)
    private Long id;
    private String name;
    @Column(name = "id_category")
    private Long idCategory;
    private String description;
    private Double price;
    @Column(name = "id_restaurant")
    private Long idRestaurant;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "is_active")
    private Boolean isActive = true;
}
