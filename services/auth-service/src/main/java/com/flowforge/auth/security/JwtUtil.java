package com.flowforge.auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private final Key key;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String userId, String orgId, String roleId) {

        return Jwts.builder()
                .setSubject(userId)
                .addClaims(Map.of(
                        "orgId", orgId,
                        "roleId", roleId
                ))
                .setIssuedAt(new Date())
                .signWith(key, SignatureAlgorithm.HS256) // 🔥 MUST MATCH GATEWAY
                .compact();
    }
}
