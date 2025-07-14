package com.foodcourt.traceabilitymanagement.infrastructure.input.rest.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityExpressions {

    public static final String OWNER = "hasRole('OWNER')";
    public static final String ADMIN = "hasRole('ADMIN')";
    public static final String CUSTOMER = "hasRole('CUSTOMER')";
    public static final String EMPLOYEE = "hasRole('EMPLOYEE')";
}
