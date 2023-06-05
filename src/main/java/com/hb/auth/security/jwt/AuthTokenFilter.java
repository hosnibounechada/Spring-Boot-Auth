package com.hb.auth.security.jwt;

import com.hb.auth.exception.ExpiredTokenException;
import com.hb.auth.model.postgres.Role;
import com.hb.auth.model.postgres.User;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.hb.auth.constant.Token.ACCESS_TOKEN;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt, ACCESS_TOKEN)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt, ACCESS_TOKEN);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    Claims claims = jwtUtils.getAllClaims(jwt, ACCESS_TOKEN);

                    User user = new User();
                    user.setId( Long.parseLong(claims.get("i").toString()));
                    user.setUsername(claims.get("u").toString());
                    user.setEmail(claims.get("e").toString());

                    user.setAuthorities(((List<String>) claims.get("r"))
                            .stream()
                            .map(Role::new)
                            .collect(Collectors.toSet()));


                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }catch(Exception e){
            log.error(e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
            return headerAuth.substring(7);
        }
        return null;
    }
}
