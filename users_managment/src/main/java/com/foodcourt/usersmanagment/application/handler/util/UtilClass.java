package com.foodcourt.usersmanagment.application.handler.util;

import com.foodcourt.usersmanagment.domain.model.ClaimUserModel;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
@UtilityClass
public class UtilClass {
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

    public static String getUserDni() {
        ClaimUserModel claims = getClaims();
        return claims.getIdentity().getDni() ;
    }
}
