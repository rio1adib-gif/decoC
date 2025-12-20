package com.digitalevidence.evidencecustody.security;

import com.digitalevidence.evidencecustody.config.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey; // Use SecretKey instead of Key for JJWT 0.12+
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey signingKey; // Updated type
    private final long jwtExpiration;

    public JwtUtil(JwtProperties properties) {
        // Generates a secure key from your secret string
        this.signingKey = Keys.hmacShaKeyFor(properties.getSecret().getBytes());
        this.jwtExpiration = properties.getExpiration();
    }

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .subject(username)            // RENAMED from setSubject
                .claim("role", role)
                .issuedAt(new Date())         // RENAMED from setIssuedAt
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration)) // RENAMED from setExpiration
                .signWith(signingKey)         // Simplified: Algorithm is inferred from the key
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()                 // REPLACED parserBuilder()
                .verifyWith(signingKey)      // REPLACED setSigningKey()
                .build()
                .parseSignedClaims(token)    // REPLACED parseClaimsJws()
                .getPayload();               // REPLACED getBody()
    }
}