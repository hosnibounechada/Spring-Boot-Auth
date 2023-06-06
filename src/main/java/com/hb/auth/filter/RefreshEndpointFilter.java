package com.hb.auth.filter;

import com.hb.auth.model.postgres.User;
import com.hb.auth.security.jwt.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.hb.auth.constant.Token.REFRESH_TOKEN;

@Component
@WebFilter(urlPatterns = "${apiPrefix}/auth/refresh")
@RequiredArgsConstructor
public class RefreshEndpointFilter implements Filter {

    private final JwtUtils jwtUtils;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Filter logic for the specific endpoint
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Retrieve the cookie with the token
        String token = jwtUtils.parseJwtFromCookie(httpRequest);

        if(token == null) token = jwtUtils.parseJwt(httpRequest);

        // test code
        if (token != null && jwtUtils.validateJwtToken(token, REFRESH_TOKEN)) {

            User user = jwtUtils.getUserFromJwt(token, REFRESH_TOKEN);

            httpRequest.setAttribute("jwtToken", token);

            httpRequest.setAttribute("userId", user.getId());
        }
        // Continue with the filter chain
        chain.doFilter(request, response);
    }
    // Other methods of the Filter interface

}
