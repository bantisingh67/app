package com.app.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.key}")
    private String algorithmKey;

    @Value("${jwt.issur}")
    private String issur;

    @Value("${jwt.expiry}")
    private int expiry;
    private Algorithm algorithm;

    @PostConstruct
    public void PostConstructer() {
        System.out.println(algorithmKey);
        System.out.println(issur);
        System.out.println(expiry);
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String generateToken(String username) {
       return JWT.create().
                withClaim("username", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiry))
                .withIssuer(issur)
                .sign(algorithm);
    }
    public String getUsername(String token) {
        DecodedJWT decodedToken = JWT.require(algorithm)
                .withIssuer(issur)
                .build()
                .verify(token);
        return decodedToken.getClaim("username").asString();
    }
}