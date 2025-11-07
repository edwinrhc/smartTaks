package com.erhc.smarttasks.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 🔐 Clave secreta (mínimo 32 caracteres para HS256)
    private static final String SECRET_KEY = "superclaveultrasegura1234567890superultrasegura";

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // ===============================
    // Generar token con rol
    // ===============================
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role) // 👈 Incluimos el rol
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ===============================
    // Extraer username
    // ===============================
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ===============================
    //Extraer rol
    // ===============================
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // ===============================
    //Extraer fecha de expiración
    // ===============================
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    // ===============================
    //Validar token
    // ===============================
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // ===============================
    //Verificar si expiró
    // ===============================
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // ===============================
    //Extraer todos los claims (payload)
    // ===============================
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new RuntimeException("Token inválido o manipulado");
        }
    }
}
