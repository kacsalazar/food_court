package com.foodcourt.usersmanagment.domain.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GetRolModel {

    private Long id;
    private String name;
    private String description;
}
