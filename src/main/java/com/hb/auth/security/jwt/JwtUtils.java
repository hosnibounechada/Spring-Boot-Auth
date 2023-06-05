package com.hb.auth.security.jwt;

import com.hb.auth.constant.Token;
import com.hb.auth.model.postgres.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtils {
    @Value("${jwt.access.key}")
    private String accessJwtSecret;
    @Value("${jwt.access.duration}")
    private int accessExpiresAfter;
    @Value("${jwt.refresh.key}")
    private String refreshJwtSecret;
    @Value("${jwt.refresh.duration}")
    private int refreshExpiresAfter;

    private static final String UNKNOWN_TOKEN_TYPE_MESSAGE = "Unknown token type";
    private Key getJwtSecretKey(String secretKey){
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
    }

    public String generateJwtToken(Authentication auth, Token tokenType) {

        String jwtSecret;
        int expiresAfter;
        switch (tokenType){
            case ACCESS_TOKEN -> {
                jwtSecret = accessJwtSecret;
                expiresAfter = accessExpiresAfter;
            }
            case REFRESH_TOKEN -> {
                jwtSecret = refreshJwtSecret;
                expiresAfter = refreshExpiresAfter;
            }
            default -> throw new IllegalArgumentException(UNKNOWN_TOKEN_TYPE_MESSAGE);
        }

        User user = (User) auth.getPrincipal();

        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Map<String, Object> extraClaims = Map.of(
                "i", user.getId(),
                "u", user.getUsername(),
                "e", user.getEmail(),
                "r", roles
        );

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(Duration.of(expiresAfter, ChronoUnit.MINUTES))))
                .signWith(getJwtSecretKey(jwtSecret))
                .compact();
    }

    public String getUserNameFromJwtToken(String token, Token tokenType) {
        return getClaim(token, Claims::getSubject, tokenType);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver, Token tokenType) {
        final Claims claims = getAllClaims(token, tokenType);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaims(String token, Token tokenType){
        String jwtSecret;
        switch (tokenType){
            case ACCESS_TOKEN -> jwtSecret = accessJwtSecret;
            case REFRESH_TOKEN -> jwtSecret = refreshJwtSecret;
            default -> throw new IllegalArgumentException(UNKNOWN_TOKEN_TYPE_MESSAGE);
        }

        return Jwts.parserBuilder()
                .setSigningKey(getJwtSecretKey(jwtSecret))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public boolean validateJwtToken(String jwt, Token tokenType) {
        String jwtSecret;

        switch (tokenType){
            case ACCESS_TOKEN -> jwtSecret = accessJwtSecret;
            case REFRESH_TOKEN -> jwtSecret = refreshJwtSecret;
            default -> throw new IllegalArgumentException(UNKNOWN_TOKEN_TYPE_MESSAGE);
        }

        try {
            getClaim(jwt, Claims::getExpiration, tokenType);
            Jwts.parserBuilder().setSigningKey(getJwtSecretKey(jwtSecret)).build().parseClaimsJws(jwt);
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return true;
    }
}
