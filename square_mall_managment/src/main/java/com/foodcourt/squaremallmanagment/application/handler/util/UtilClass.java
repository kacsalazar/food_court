package com.foodcourt.squaremallmanagment.application.handler.util;

import com.foodcourt.squaremallmanagment.domain.model.ClaimsUserModel;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
@UtilityClass
public class UtilClass {

    public ClaimsUserModel getClaims() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof ClaimsUserModel) {
                return (ClaimsUserModel) principal;
            }
        }
        return null;
    }

    public static String getUserDni() {
        ClaimsUserModel claims = getClaims();
        return claims.getIdentity().getDni() ;
    }
}
