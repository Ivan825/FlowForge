package com.flowforge.gateway.filter;

import com.flowforge.gateway.security.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthGatewayFilterFactory
        extends AbstractGatewayFilterFactory<JwtAuthGatewayFilterFactory.Config> {

    private final JwtUtil jwtUtil;

    public JwtAuthGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            String authHeader = exchange.getRequest()
                    .getHeaders()
                    .getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String token = authHeader.substring(7);

            try {
                Claims claims = jwtUtil.extractClaims(token);

                ServerHttpRequest mutatedRequest = exchange.getRequest()
                        .mutate()
                        .header("X-User-Id", claims.getSubject())
                        .header("X-Org-Id", claims.get("orgId", String.class))
                        .header("X-Role", claims.get("roleId", String.class))
                        .build();

                return chain.filter(exchange.mutate().request(mutatedRequest).build());

            } catch (Exception e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };
    }

    public static class Config {
    }
}
