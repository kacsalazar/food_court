package com.foodcourt.squaremallmanagment.application.handler.helper;

import com.foodcourt.squaremallmanagment.domain.model.ClaimsUserModel;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelperClass {

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


    public String getUserDni() {
        ClaimsUserModel claims = getClaims();
        return claims.getIdentity().getDni() ;
    }
}
