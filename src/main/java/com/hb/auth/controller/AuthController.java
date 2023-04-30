package com.hb.auth.controller;

import com.hb.auth.payload.request.auth.RegisterRequest;
import com.hb.auth.payload.request.user.LoginRequest;
import com.hb.auth.payload.response.auth.LoginResponse;
import com.hb.auth.payload.response.user.UserResponse;
import com.hb.auth.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hb.auth.util.JwtUtil.assignJwtToCookie;

@RestController
@RequestMapping("${apiPrefix}/auth")
@Tag(name = "auth", description = "the auth API")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest body){
        return authService.registerUser(body.firstName(), body.lastName(),body.age(),body.email(), body.password());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest body, HttpServletResponse response) {
        LoginResponse loginResponse = authService.loginUser(body.username(), body.password(), response);

        if (loginResponse.userViewImp() != null) {
            assignJwtToCookie(loginResponse.jwt(), response);
        }

        return ResponseEntity.ok(loginResponse);
    }
}
