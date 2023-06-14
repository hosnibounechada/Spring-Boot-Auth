package com.hb.auth.security.oauth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OAuth2AuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }

        // Retrieve the OAuth2LoginAuthenticationToken
        OAuth2LoginAuthenticationToken oauthToken = (OAuth2LoginAuthenticationToken) authentication;

        System.out.println();
        // Retrieve the user details from the authentication token
        // You may need to access the user's email, name, or any other relevant information from the token
        //String email = oauthToken.getPrincipal().getAttribute("email");
        String email = "test@test.gmail.com";
        // Perform your authentication logic here, such as querying the database or external service
        // Validate the user's email and other information if needed
        // You can also perform additional checks or verifications

        if (email == null || email.isEmpty()) {
            throw new BadCredentialsException("Email not provided or invalid.");
        }

        // Create an authenticated Authentication object, such as UsernamePasswordAuthenticationToken
        // You can set the principal, credentials, and authorities as per your requirements
        // In this example, we set the email as the principal and "ROLE_USER" as the authority
        OAuth2LoginAuthenticationToken authenticatedToken =
                new OAuth2LoginAuthenticationToken(((OAuth2LoginAuthenticationToken) authentication).getClientRegistration(), ((OAuth2LoginAuthenticationToken) authentication).getAuthorizationExchange());

        return authenticatedToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OAuth2LoginAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
