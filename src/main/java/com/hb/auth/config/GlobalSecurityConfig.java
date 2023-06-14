package com.hb.auth.config;

import com.hb.auth.security.oauth.OAuth2AuthenticationProvider;
import com.hb.auth.security.service.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class GlobalSecurityConfig {
    private final UserServiceImp userDetailsService;

    @Value("${apiPrefix}")
    private String apiPrefix;

    @Bean
    public RequestMatcher[] ignoredRequestMatchers() {
        return List.of(
                AntPathRequestMatcher.antMatcher("/"),
                AntPathRequestMatcher.antMatcher("/favicon.ico"),
                AntPathRequestMatcher.antMatcher("/site/**"),
                AntPathRequestMatcher.antMatcher("/h2-console/**"),
                AntPathRequestMatcher.antMatcher("/graphql/**"),
                AntPathRequestMatcher.antMatcher("/graphiql/**"),
                AntPathRequestMatcher.antMatcher("/swagger-ui/**"),
                AntPathRequestMatcher.antMatcher("/api-docs/**"),
                AntPathRequestMatcher.antMatcher(apiPrefix + "/confirmation/**"),
                AntPathRequestMatcher.antMatcher(apiPrefix + "/auth/**"),
                AntPathRequestMatcher.antMatcher(apiPrefix + "/users/**")
        ).toArray(new RequestMatcher[0]);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(daoAuthenticationProvider);
    }*/
    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> providers = new ArrayList<>();

        // Add the DaoAuthenticationProvider
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        providers.add(daoAuthenticationProvider);

        // Add the OAuth2AuthenticationProvider
        OAuth2AuthenticationProvider oauth2AuthenticationProvider = new OAuth2AuthenticationProvider();

        providers.add(oauth2AuthenticationProvider);

        return new ProviderManager(providers);
    }
}
