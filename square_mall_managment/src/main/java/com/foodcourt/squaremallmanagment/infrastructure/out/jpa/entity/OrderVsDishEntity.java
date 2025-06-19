package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "order_items")
@Entity
public class OrderVsDishEntity {

    @Id
    private Long id;
    @Column(name = "id_order")
    private Long idOrder;
    @Column(name = "id_dish")
    private Long idDish;
    private Integer quantity;
}
