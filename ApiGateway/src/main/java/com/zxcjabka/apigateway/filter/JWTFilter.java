package com.zxcjabka.apigateway.filter;

import com.zxcjabka.apigateway.filter.misc.JWTService;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;


@Component
public class JWTFilter extends AbstractGatewayFilterFactory<JWTFilter.Config> {

    private final JWTService jwtService;

    public JWTFilter(JWTService jwtService) {
        super(Config.class);
        this.jwtService = jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = extractToken(exchange.getRequest());

            if (token == null || !jwtService.validateJWT(token)) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            Long userId = jwtService.extractId(token);
            ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                    .header("X-User-Id", userId.toString())
                    .build();

            return chain.filter(
                    exchange.mutate().request(modifiedRequest).build()
            );
        };
    }

    private String extractToken(ServerHttpRequest request) {
        String authHeader = request.getHeaders().getFirst("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        String tokenParam = request.getQueryParams().getFirst("token");
        if (tokenParam != null) {
            return tokenParam;
        }
        return null;
    }

    public static class Config {
    }
}
