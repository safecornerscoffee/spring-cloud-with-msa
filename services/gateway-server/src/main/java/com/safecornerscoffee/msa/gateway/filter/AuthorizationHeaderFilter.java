package com.safecornerscoffee.msa.gateway.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.util.List;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final Logger log = LoggerFactory.getLogger(AuthorizationHeaderFilter.class);
    private final Environment env;

    public AuthorizationHeaderFilter(Environment env) {
        super(Config.class);
        this.env = env;
    }

    public static class Config {

    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }

            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = authorizationHeader.replace("Bearer", "");

            if (!isJwtValid(jwt)) {
                return onError(exchange, "Jwt token is not valid", HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange);
        }));
    }

    private boolean isJwtValid(String jwt) {
        boolean result = true;

        String secretKeyString = env.getProperty("token.secret-key");
        byte[] secretKeyBytes = Decoders.BASE64.decode(secretKeyString);
        SecretKey key = Keys.hmacShaKeyFor(secretKeyBytes);

        String subject = null;
        try {
            subject = Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(jwt).getBody()
                    .getSubject();
        } catch (Exception e) {
            result = false;
        }

        if (subject == null || subject.isEmpty()) {
            result = false;
        }
        log.info("authenticated user: {}", subject);
        return result;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        log.error(err);
        return response.setComplete();
    }
}
