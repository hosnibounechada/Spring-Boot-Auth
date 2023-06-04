package com.hb.auth.config;

import com.hb.auth.filter.RefreshEndpointFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    @Value("${apiPrefix}")
    private String apiPrefix;
    private final JwtDecoder jwtDecoder;


    @Bean
    public FilterRegistrationBean<RefreshEndpointFilter> refreshEndpointFilterRegistration() {
        FilterRegistrationBean<RefreshEndpointFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RefreshEndpointFilter(jwtDecoder));

        registrationBean.addUrlPatterns(apiPrefix + "/auth/refresh");

        return registrationBean;
    }
}
