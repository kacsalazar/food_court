package com.foodcourt.usersmanagment.infrastructure.out.jpa.entity;

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
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "users")
@Entity
public class UserEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_role")
    private Long id_rol;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String dni;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "birthday_date")
    private Date birthdayDate;
    private String email;
    private String password;
}
