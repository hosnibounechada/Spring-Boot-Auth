package com.hb.auth.config;

import com.hb.auth.interceptor.RequestBodyValidationInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final RequestBodyValidationInterceptor requestBodyValidationInterceptor;

    public WebMvcConfig(RequestBodyValidationInterceptor requestBodyValidationInterceptor) {
        this.requestBodyValidationInterceptor = requestBodyValidationInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(requestBodyValidationInterceptor).addPathPatterns("/api/v1/**");
    }
}
