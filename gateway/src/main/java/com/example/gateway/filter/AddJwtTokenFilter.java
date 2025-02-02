package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component("customAddJwtTokenFilter")
public class AddJwtTokenFilter implements GatewayFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = getJwtToken();

        exchange.getRequest().mutate()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();

        return chain.filter(exchange);
    }
    private String getJwtToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the authentication is an OAuth2 token (OAuth2AuthenticationToken)
        if (authentication != null && authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;

            // Get the credentials (JWT token) from the authentication token
            Object principal = oauth2Token.getPrincipal();
            if (principal instanceof OAuth2User) {
                OAuth2User oauth2User = (OAuth2User) principal;
                // Assuming the JWT token is stored as an attribute in the OAuth2User
                return (String) oauth2User.getAttributes().get("access_token"); // Replace with your attribute name
            }
        }

        // If the token is not found in the context, return null or handle it as per your logic
        return null;
    }
}
