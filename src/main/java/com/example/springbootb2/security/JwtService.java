package com.example.springbootb2.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class JwtService {
    @Value("${app.jwtSecret}")
    private String jwtSecret;
    @Value("${app.jwtExp}")
    private long jwtExp;

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExp))
                .claim("role", role)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(UTF_8)))
                .compact();
    }

    public void validateJwt(String jwt) {
        Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(UTF_8)))
                .build().parseClaimsJws(jwt);
    }

    public String getUsernameFromJwt(String jwt) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(UTF_8)))
                .build().parseClaimsJws(jwt)
                .getBody().getSubject();
    }
    public String getRoleFromJwt(String jwt) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(UTF_8)))
                .build().parseClaimsJws(jwt)
                .getBody().get("role", String.class);
    }
}
