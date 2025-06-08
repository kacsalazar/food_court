package com.foodcourt.usersmanagment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClaimUserModel {

    private String email;
    private String name;
    private Long id;
    private Long idRole;
    private String roleName;
}
