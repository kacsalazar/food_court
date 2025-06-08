package com.foodcourt.squaremallmanagment.infrastructure.out.auth;

import com.foodcourt.squaremallmanagment.domain.api.IUserClientServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final IUserClientServicePort userClientServicePort; // esta es tu clase que consulta a /user


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);
        if (!jwtService.isValidToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Long userId = jwtService.extractUserId(token);
        Long roleId = jwtService.extractRoleId(token);
        String roleName = jwtService.extractRoleName(token);

        log.info("User ID: {}, Role Name: {}", userId, roleName);

        log.info("Validating user with ID: {} and role: {}", userClientServicePort.isValidUser(userId, roleName));
        if (!userClientServicePort.isValidUser(userId, roleName)) {
            log.info("ENTRA");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        log.info("POR FUERA DEL MÉTODO");
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(roleName));

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userId, null, authorities);
        log.info("POR FUERA DEL MÉTODO 2");
        SecurityContextHolder.getContext().setAuthentication(authToken);
        log.info("POR FUERA DEL MÉTODO 3");
        filterChain.doFilter(request, response);
        log.info("Filter chain executed successfully");
    }
}
