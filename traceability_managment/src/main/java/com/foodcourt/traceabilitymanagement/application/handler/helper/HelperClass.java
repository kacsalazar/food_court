package com.foodcourt.traceabilitymanagement.application.handler.helper;

import com.foodcourt.traceabilitymanagement.domain.model.ClaimUserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelperClass {
    public ClaimUserModel getClaims() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof ClaimUserModel) {
                return (ClaimUserModel) principal;
            }
        }
        return null;
    }


    public String getUserDni() {
        ClaimUserModel claims = getClaims();
        return claims.getIdentity().getDni() ;
    }
}
