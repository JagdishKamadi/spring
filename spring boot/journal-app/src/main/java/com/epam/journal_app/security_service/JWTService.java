package com.epam.journal_app.security_service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    @Value("${secret.key}")
    private String secretKey;

    public String generateToken(String username) {
        return createToken(new HashMap(), username);
    }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(40))) // setting the expiry for 5 minute
                .signWith(key())
                .compact();
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private Boolean validateToken(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean isTokenExpire(String token) {
        return !validateToken(token);
    }
}
