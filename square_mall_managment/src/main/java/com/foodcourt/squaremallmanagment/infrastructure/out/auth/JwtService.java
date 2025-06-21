package com.foodcourt.squaremallmanagment.infrastructure.out.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodcourt.squaremallmanagment.application.handler.ITokenValidator;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Service
@RequiredArgsConstructor
public class JwtService implements ITokenValidator {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public Boolean isValidToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }

    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

}
