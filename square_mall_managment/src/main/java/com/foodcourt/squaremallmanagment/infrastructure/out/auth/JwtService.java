package com.foodcourt.squaremallmanagment.infrastructure.out.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;


@Component
public class JwtService {
    private final String secretKey;
    private final ObjectMapper objectMapper;

    public JwtService(@Value("${jwt.secret}") String secretKey) {
        this.secretKey = secretKey;
        this.objectMapper = new ObjectMapper();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    private JsonNode getUserNode(String token) {
        try {
            Claims claims = extractAllClaims(token);
            String userJson = claims.get("User", String.class);
            return objectMapper.readTree(userJson);
        } catch (Exception e) {
            // puedes loguear el error si quieres
            return null;
        }
    }

    public Long extractUserId(String token) {
        JsonNode userNode = getUserNode(token);
        if (userNode != null && userNode.has("id")) {
            return userNode.get("id").asLong();
        }
        return null;
    }

    public Long extractRoleId(String token) {
        JsonNode userNode = getUserNode(token);
        if (userNode != null && userNode.has("idRole")) {
            return userNode.get("idRole").asLong();
        }
        return null;
    }

    public String extractRoleName(String token) {
        JsonNode userNode = getUserNode(token);
        if (userNode != null && userNode.has("roleName")) {
            return userNode.get("roleName").asText();
        }
        return null;
    }

    public String extractEmail(String token) {
        JsonNode userNode = getUserNode(token);
        if (userNode != null && userNode.has("email")) {
            return userNode.get("email").asText();
        }
        return null;
    }

    public String extractName(String token) {
        JsonNode userNode = getUserNode(token);
        if (userNode != null && userNode.has("name")) {
            return userNode.get("name").asText();
        }
        return null;
    }

    public boolean isValidToken(String token) {
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
}
