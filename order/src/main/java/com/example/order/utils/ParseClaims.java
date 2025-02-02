package com.example.order.utils;

import org.springframework.stereotype.Component;
import org.keycloak.TokenVerifier;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import java.util.Map;

@Component
public class ParseClaims {
    public String getClaims(String jwt) {
        String token = jwt.replace("Bearer ", "");

        try {
            AccessToken accessToken = TokenVerifier.create(token, AccessToken.class).getToken();
            return accessToken.getSubject();
        } catch (VerificationException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
