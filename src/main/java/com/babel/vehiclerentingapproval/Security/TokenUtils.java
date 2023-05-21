package com.babel.vehiclerentingapproval.Security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Clase de utilidad para trabajar con tokens JWT.
 */
public class TokenUtils {

    /**
     * Clave secreta utilizada para firmar y verificar los tokens JWT.
     */
    private final static Key ACCESS_TOKEN_SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Duración de validez del token de acceso en segundos.
     */
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L; // Validez del token por 30 días

    /**
     * Crea un token JWT utilizando el nombre y el correo electrónico proporcionados.
     *
     * @param nombre Nombre del usuario.
     * @param email  Correo electrónico del usuario.
     * @return Token JWT generado.
     */
    public static String createToken(String nombre, String email) {
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(ACCESS_TOKEN_SECRET_KEY)
                .compact();
    }

    /**
     * Obtiene la autenticación de usuario a partir de un token JWT.
     *
     * @param token Token JWT.
     * @return Objeto UsernamePasswordAuthenticationToken si el token es válido, o null si el token es inválido.
     */
    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }
    }
}
