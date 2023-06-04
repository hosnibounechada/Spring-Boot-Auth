package com.hb.auth.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import java.io.IOException;

@WebFilter(urlPatterns = "${apiPrefix}/auth/refresh")
@RequiredArgsConstructor
public class RefreshEndpointFilter implements Filter {

    private final JwtDecoder jwtDecoder;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Filter logic for the specific endpoint
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Retrieve the cookie with the token
        Cookie[] cookies = httpRequest.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jwt")) {
                    token = cookie.getValue();
                }
            }
        }

        // Perform further processing with the token
        if (token != null) {
            httpRequest.setAttribute("jwtToken", token);
            Jwt jwt = jwtDecoder.decode(token);
            String userId = jwt.getClaim("sub");
        }
        // Continue with the filter chain
        chain.doFilter(request, response);
    }
    // Other methods of the Filter interface
    private String extractJwtTokenFromCookie(String cookieContent) {
        String jwtToken = null;

        String[] cookieParts = cookieContent.split(";");
        for (String cookiePart : cookieParts) {
            String[] keyValue = cookiePart.trim().split("=");
            if (keyValue.length == 2 && keyValue[0].equals("jwt")) {
                jwtToken = keyValue[1];
                break;
            }
        }

        return jwtToken;
    }
}
