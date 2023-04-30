package com.hb.auth.config;

import com.hb.auth.filter.SnakeToCamelCaseFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

//@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean<SnakeToCamelCaseFilter> snakeToCamelCaseFilterRegistration() {
        FilterRegistrationBean<SnakeToCamelCaseFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new SnakeToCamelCaseFilter());
        registration.addUrlPatterns("/api/v1/*"); // set URL patterns to filter
        return registration;
    }
}
