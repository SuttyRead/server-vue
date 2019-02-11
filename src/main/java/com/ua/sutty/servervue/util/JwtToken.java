package com.ua.sutty.servervue.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ua.sutty.servervue.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;

@Component
public class JwtToken {

    @Value("${jwt.key}")
    private String jwtKey;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(jwtKey);
        String jwt = JWT.create()
                .withClaim("username", user.getUsername())
                .withClaim("role", user.getRole().name())
                .sign(algorithm);
        return jwt;
    }

    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

}
