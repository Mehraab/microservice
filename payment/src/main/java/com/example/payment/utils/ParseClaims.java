package com.example.payment.utils;

import org.keycloak.TokenVerifier;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Component;

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
