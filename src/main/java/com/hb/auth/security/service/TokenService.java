package com.hb.auth.security.service;


import com.hb.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder jwtEncoder;

    public String generateJwt(Authentication auth){

        Instant now = Instant.now();

        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        User user = (User) auth.getPrincipal();

        Consumer<Map<String, Object>> userMap = stringObjectMap -> {
            stringObjectMap.put("i",user.getId());
            stringObjectMap.put("u",user.getUsername());
            stringObjectMap.put("e",user.getEmail());
        };

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
//                .subject(auth.getName())
                .subject(user.getId().toString())
                .claim("roles", scope)
                .claims(userMap)
                .expiresAt(Instant.now().plus(Duration.of(2, ChronoUnit.MINUTES)))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
