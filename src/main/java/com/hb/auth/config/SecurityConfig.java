package com.hb.auth.config;

import com.hb.auth.security.jwt.AuthEntryPointJwt;
import com.hb.auth.security.jwt.AuthTokenFilter;
import com.hb.auth.security.oauth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static com.hb.auth.constant.Roles.ADMIN;
import static com.hb.auth.constant.Roles.USER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final RequestMatcher[] ignoredRequestMatchers;

    private final AuthEntryPointJwt authEntryPointJwt;
    private final AuthTokenFilter authTokenFilter;
    private final AuthenticationManager authenticationManager;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    private final CustomOAuth2UserService oauth2UserService;

    @Value("${apiPrefix}")
    private String apiPrefix;

    @Bean(name = "SecurityFilterChainJWT")
    SecurityFilterChain securityFilterChain(HttpSecurity http/*,
    OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2LoginHandler,
    OAuth2UserService<OidcUserRequest, OidcUser> oidcLoginHandler*/
    ) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers(ignoredRequestMatchers))
                .headers(headers -> headers.frameOptions().disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ignoredRequestMatchers).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher(apiPrefix + "/admin/**")).hasRole(ADMIN)
                        .requestMatchers(AntPathRequestMatcher.antMatcher(apiPrefix + "/user/**")).hasAnyRole(ADMIN, USER)
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
//                        Customizer.withDefaults()
//                                .loginPage("/api/v1/auth/login")
//                                .authorizationEndpoint()
//                                .baseUri("/oauth2/authorize")
//                                .authorizationRequestRepository(null)
//                                .and()
//                                .redirectionEndpoint()
//                                .baseUri("oauth2/callback/*")
//                                .and()
//                                .userInfoEndpoint()
//                                .userService(oauth2UserService)
//                                .and()
                                .successHandler(authenticationSuccessHandler)
                                .failureHandler(authenticationFailureHandler)
                )
                .authenticationManager(authenticationManager)
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPointJwt);

        return http.build();
    }

    @Bean
    ApplicationListener<AuthenticationSuccessEvent> successLogger() {
        return event -> log.info("success {}", event.getAuthentication());
    }
}
