package com.hb.auth.controller;

import com.hb.auth.annotation.swagger.auth.*;
import com.hb.auth.payload.request.auth.*;
import com.hb.auth.payload.request.auth.LoginRequest;
import com.hb.auth.payload.response.auth.EmailAvailabilityResponse;
import com.hb.auth.payload.response.auth.LoginResponse;
import com.hb.auth.payload.response.user.UserResponse;
import com.hb.auth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.hb.auth.util.JwtUtil.assignJwtToCookie;

@RestController
@RequestMapping("${apiPrefix}/auth")
@AuthControllerSwagger
@Validated
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @RegisterSwagger
    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest body) {
        return ResponseEntity.ok(authService.registerUser(body.firstName(), body.lastName(), body.age(), body.email(), body.password()));
    }

    /*@RegisterSwagger
    @PostMapping(value = "/register", consumes = "application/json")
    public RedirectView registerAndRedirect(@RequestBody RegisterRequest body*//*, RedirectAttributes attributes*//*) {
        authService.registerUser(body.firstName(), body.lastName(), body.age(), body.email(), body.password());
        //attributes.addAttribute("email", body.email());
        return new RedirectView("http://localhost:8080/api/v1/confirmation/email/"+body.email());
    }*/

    @LoginSwagger
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest body, HttpServletResponse response) {
        LoginResponse loginResponse = authService.loginUser(body.username(), body.password(), response);

        //if (loginResponse.userResponse() != null) assignJwtToCookie(loginResponse.jwt(), response);

        return ResponseEntity.ok(loginResponse);
    }

    @ConfirmEmailSwagger
    @PostMapping("/ConfirmEmail")
    public ResponseEntity<Boolean> confirmEmail(@RequestBody ConfirmEmailRequest body) {
        return ResponseEntity.ok(authService.confirmEmail(body.email(), body.code()));
    }

    @SendPhoneOTPSwagger
    @PostMapping("/sendOTP")
    public ResponseEntity<Boolean> sendPhoneOTP(@RequestBody ConfirmPhoneRequest body) {
        return ResponseEntity.ok(authService.sendOTP(body.phone()));
    }

    @VerifyPhoneOTPSwagger
    @PostMapping("/verifyOTP")
    public ResponseEntity<Boolean> verifyPhoneOTP(@RequestBody VerifyPhoneRequest body, Principal principal) {
        return ResponseEntity.ok(authService.verifyOTP(Long.parseLong(principal.getName()), body.phone(), body.otp()));
    }

    @EmailAvailabilitySwagger
    @PostMapping("/email")
    public ResponseEntity<EmailAvailabilityResponse> emailAvailability(@RequestBody EmailAvailabilityRequest body) {
        return ResponseEntity.ok(authService.emailAvailability(body.email()));
    }
    @LoginSwagger
    @PostMapping("/me")
    public ResponseEntity<LoginResponse> me() {
        return ResponseEntity.ok(authService.me());
    }
    @GetMapping("/refresh")
    public ResponseEntity<String> refresh(@RequestAttribute("jwtToken") String token) {
        return ResponseEntity.ok(token);
    }
}
