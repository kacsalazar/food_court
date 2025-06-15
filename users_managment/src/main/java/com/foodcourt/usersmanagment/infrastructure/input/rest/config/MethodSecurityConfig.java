package com.foodcourt.usersmanagment.infrastructure.input.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig {

    //Se utiliza para habilitar la seguridad a nivel de méthodo
    // esta clase configura Spring para que puedas proteger métodos individuales usando anotaciones de seguridad.
}
