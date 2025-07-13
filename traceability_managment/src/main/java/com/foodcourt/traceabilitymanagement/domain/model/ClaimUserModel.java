package com.foodcourt.traceabilitymanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClaimUserModel {


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
        private String dni;
    }

    @NoArgsConstructor
    @Data
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Authorization {
        private Long idRole;
        private String roleName;
    }

}
