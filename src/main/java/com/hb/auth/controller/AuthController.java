package com.hb.auth.controller;

import com.hb.auth.model.User;
import com.hb.auth.payload.request.auth.*;
import com.hb.auth.payload.request.user.LoginRequest;
import com.hb.auth.payload.response.auth.LoginResponse;
import com.hb.auth.payload.response.user.UserResponse;
import com.hb.auth.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.hb.auth.util.JwtUtil.assignJwtToCookie;

@RestController
@RequestMapping("${apiPrefix}/auth")
@Tag(name = "auth", description = "the auth API")
@Validated
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

    @PostMapping("/ConfirmEmail")
    public ResponseEntity<Boolean> confirmEmail(@Valid @RequestBody ConfirmEmailRequest body) {
        return ResponseEntity.ok(authService.confirmEmail(body.email(), body.code()));
    }

    @PostMapping ("/sendOTP")
    public ResponseEntity<Boolean> sendPhoneOTP(@Valid @RequestBody ConfirmPhoneRequest body) {
        return ResponseEntity.ok(authService.sendOTP(body.phone()));
    }

    @PostMapping("/verifyOTP")
    public ResponseEntity<Boolean> verifyOTP(@Valid @RequestBody VerifyPhoneRequest body) {
        return ResponseEntity.ok(authService.verifyOTP(body.phone(), body.otp()));
    }
}
