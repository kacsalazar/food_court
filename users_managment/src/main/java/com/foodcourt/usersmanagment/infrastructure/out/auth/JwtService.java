package com.foodcourt.usersmanagment.infrastructure.out.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodcourt.usersmanagment.domain.model.ClaimUserModel;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {

    //verificar esta importación
    @Value("${jwt.secret}")
    private String jwtSecret;

    private final ObjectMapper objectMapper;

    public String generateToken(ClaimUserModel token){

        Map<String, Object> claims = new HashMap<>();
        try {
            claims.put("User", objectMapper.writeValueAsString(token));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing user to JSON", e);
        }

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(token.getEmail())
                .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }


    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }

    public UserEntity getUserFromToken(String token) {
        Claims claims = validateToken(token);
        try {
            return objectMapper.readValue(claims.get("User", String.class), UserEntity.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error deserializing user from JWT token", e);
        }

    }
}
