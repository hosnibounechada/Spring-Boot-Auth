package com.hb.auth.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb.auth.payload.request.BaseRequestDTO;
import com.hb.auth.payload.request.user.CreateUserRequest;
import com.hb.auth.payload.request.user.UpdateUserRequest;
import com.hb.auth.validator.ObjectValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.stream.Collectors;

import static com.hb.auth.constant.HttpMethod.POST;
import static com.hb.auth.constant.HttpMethod.PUT;

@Component
@Slf4j
public class RequestBodyValidationInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper;

    public RequestBodyValidationInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Interceptor has been triggered !!!");
        /*String method = request.getMethod().toLowerCase();
        String uri = request.getRequestURI();

        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        BaseRequestDTO requestDTO = null;
        if (uri.startsWith("/api/v1/users")) {
                switch (method) {
                    case POST -> requestDTO = objectMapper.readValue(requestBody, CreateUserRequest.class);
                    case PUT -> requestDTO = objectMapper.readValue(requestBody, UpdateUserRequest.class);
                }
                ObjectValidator.validate(requestDTO);
        }*/

        return true; // Or false to stop processing the request
    }

    private Class<? extends BaseRequestDTO> getRequestDtoClass(String method) {
        return switch (method) {
            case POST -> CreateUserRequest.class;
            case PUT -> UpdateUserRequest.class;
            default -> null;
        };
    }
}
