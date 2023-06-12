package com.hb.auth.filter;

import com.hb.auth.security.jwt.AuthEntryPointJwt;
import com.hb.auth.security.jwt.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static com.hb.auth.constant.Roles.ADMIN;
import static com.hb.auth.constant.Roles.USER;

@Configuration
@RequiredArgsConstructor
public class SecurityFilterChainJWT {
    /*
    private final RequestMatcher[] ignoredRequestMatchers;

    private final AuthEntryPointJwt authEntryPointJwt;
    private final AuthTokenFilter authTokenFilter;
    private final AuthenticationManager authenticationManager;


    @Value("${apiPrefix}")
    private String apiPrefix;

    @Bean(name = "SecurityFilterChainJWT")
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers(ignoredRequestMatchers))
                .headers(headers -> headers.frameOptions().disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ignoredRequestMatchers).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher(apiPrefix + "/admin/**")).hasRole(ADMIN)
                        .requestMatchers(AntPathRequestMatcher.antMatcher(apiPrefix + "/user/**")).hasAnyRole(ADMIN, USER)
                        .anyRequest().authenticated()
                )
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPointJwt);
        http
                .authenticationManager(authenticationManager)
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }*/
}
