package com.flowforge.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {

    private final Key key = Keys.hmacShaKeyFor(
            "very-secret-key-change-this-later-123456".getBytes()
    );

    public String generateToken(String userId, String orgId, String roleId) {
        return Jwts.builder()
                .setSubject(userId)
                .claim("orgId", orgId)
                .claim("roleId", roleId)
                .signWith(key)
                .compact();
    }

    public Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
