package com.foodcourt.squaremallmanagment.infrastructure.input.rest.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodcourt.squaremallmanagment.application.handler.ITokenValidator;
import com.foodcourt.squaremallmanagment.domain.model.ClaimsUserModel;
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

    private final ITokenValidator tokenValidator;

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
        if (!tokenValidator.isValidToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String [] parts = token.split("\\.");
        if(parts.length < 2) {
            throw new IllegalArgumentException("Invalid JWT token format");
        }

        String payload = new String (java.util.Base64.getDecoder().decode(parts[1]));
        ObjectMapper mapper = new ObjectMapper();
        ClaimsUserModel claims = mapper.readValue(payload, ClaimsUserModel.class);

        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(claims.getAuthorization().getRoleName()));

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(claims.getIdentity().getId(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}
