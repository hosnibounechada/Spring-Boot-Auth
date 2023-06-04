package com.hb.auth.security.jwt;

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
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private Key getJwtSecretKey(String secretKey){
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
    }

    public String generateJwtToken(Authentication auth, boolean isAccessToken) {

        String jwtSecret = isAccessToken ? accessJwtSecret : refreshJwtSecret;
        int expiresAfter = isAccessToken ? accessExpiresAfter : refreshExpiresAfter;

        User user = (User) auth.getPrincipal();

        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        Map<String, Object> extraClaims = new HashMap<>() {
            {
                put("i", user.getId());
                put("u", user.getUsername());
                put("e", user.getEmail());
                put("r", roles);
            }
        };

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expiresAfter))
                .signWith(getJwtSecretKey(jwtSecret))
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token, true);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaims(String token, boolean isAccessToken){
        String jwtSecret = isAccessToken ? accessJwtSecret : refreshJwtSecret;

        return Jwts.parserBuilder()
                .setSigningKey(getJwtSecretKey(jwtSecret))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public boolean validateJwtToken(String jwt, boolean isAccessToken) {
        String jwtSecret = isAccessToken ? accessJwtSecret : refreshJwtSecret;
        try {
            getClaim(jwt, Claims::getExpiration);
            Jwts.parserBuilder().setSigningKey(getJwtSecretKey(jwtSecret)).build().parseClaimsJws(jwt);
            return true;
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
        return false;
    }
}
