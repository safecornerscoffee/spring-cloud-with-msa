package com.safecornerscoffee.msa.gateway;

import com.safecornerscoffee.msa.gateway.filter.CustomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.addOriginalRequestUrl;

@Configuration
public class FilterConfig {

    private final CustomFilter customFilter;

    public FilterConfig(CustomFilter customFilter) {
        this.customFilter = customFilter;
    }

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r ->
                        r.path("/users/**")
                        .filters(f -> f.filter((exchange, chain) -> {
                                ServerHttpRequest req = exchange.getRequest();
                                addOriginalRequestUrl(exchange, req.getURI());
                                String path = req.getURI().getRawPath();
                                String newPath = path.replaceAll(
                                        "/users/(?<segment>.*)",
                                        "/${segment}"
                                );
                                ServerHttpRequest request = req.mutate().path(newPath).build();
                                exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, request.getURI());
                                return chain.filter(exchange.mutate().request(request).build());
                            })
                            .addRequestHeader("user-service-request", "user-service-request-header")
                            .addResponseHeader("user-service-response", "user-service-response-header")
                            .filter(customFilter.apply(config -> {}))
                        )
                        .uri("lb://user-service"))
                .route(r ->
                        r.path("/orders/**")
                        .filters(f -> f.filter( (exchange, chain) -> {
                                ServerHttpRequest req = exchange.getRequest();
                                addOriginalRequestUrl(exchange, req.getURI());
                                String path = req.getURI().getRawPath();
                                String newPath = path.replaceAll(
                                        "/orders/(?<sequence>.*)",
                                        "/${sequence}"
                                );
                                ServerHttpRequest request = req.mutate().path(newPath).build();
                                exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, request.getURI());
                                return chain.filter(exchange.mutate().request(request).build());

                            })
                            .addResponseHeader("order-service-request", "order-service-request-header")
                            .addResponseHeader("order-service-response", "order-service-response-header"))
                        .uri("lb://order-service"))
                .route(r ->
                        r.path("/products/**")
                                .filters(f -> f.filter( (exchange, chain) -> {
                                    ServerHttpRequest req = exchange.getRequest();
                                    addOriginalRequestUrl(exchange, req.getURI());
                                    String path = req.getURI().getRawPath();
                                    String newPath = path.replaceAll(
                                            "/products/(?<sequence>.*)",
                                            "/${sequence}"
                                    );
                                    ServerHttpRequest request = req.mutate().path(newPath).build();
                                    exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, request.getURI());
                                    return chain.filter(exchange.mutate().request(request).build());

                                })
                                .addResponseHeader("product-service-request", "product-service-request-header")
                                .addResponseHeader("product-service-response", "product-service-response-header"))
                        .uri("lb://product-service"))
                .build();

    }
}
