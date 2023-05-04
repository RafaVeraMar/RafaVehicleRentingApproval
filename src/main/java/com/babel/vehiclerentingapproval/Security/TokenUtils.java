package com.babel.vehiclerentingapproval.Security;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


/*
@TODO Sería conveniente establecer la vida util del token en application.properties
 */
public class TokenUtils {

    private final static String ACCESS_TOKEN_SECRET = "v8R9zMfB77EUQChNdKHuDcGWtg4i7m";
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;// validez del token por 30 dias

    /**
     *
     * @param nombre
     * @param email
     * @return
     */
    public static String createToken(String nombre, String email){
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();


    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
     try{
         Claims claims = Jwts.parserBuilder()
                 .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                 .build()
                 .parseClaimsJws(token)
                 .getBody();

         String email = claims.getSubject();
         return new UsernamePasswordAuthenticationToken((email),null, Collections.emptyList());
     } catch(JwtException e) {
         return null;
     }

    }
}
