package com.foodcourt.squaremallmanagment.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClaimsUserModel {


    private Identity identity;
    private Authorization authorization;
    private Long exp;

    @NoArgsConstructor
    @Data
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Identity {
        private String email;
        private String name;
        private Long id;
        private String dni;
    }

    @NoArgsConstructor
    @Data
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Authorization {
        private String idRole;
        private String roleName;
    }

}
