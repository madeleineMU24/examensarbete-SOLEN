package com.example.sol_guide;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {

    private final SecretKey secretKey;
    private final long expirationTime;

    public JwtService(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long expirationTime) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationTime = expirationTime;
    }

    public String generateToken (String username, List<String> roles){
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public boolean isTokenValid(String token){
        try {
            getClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String getUsername(String token){
        return getClaims(token).getSubject();
    }

    public List<String> getRoles(String token){
        Claims claims = getClaims(token);
        return claims.get("roles", List.class);
    }

    public List<SimpleGrantedAuthority> getAuthorities(String token){
        List<String> roles = getRoles(token);
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

    }

}
