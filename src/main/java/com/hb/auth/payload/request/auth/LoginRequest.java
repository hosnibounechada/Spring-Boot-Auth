package com.hb.auth.payload.request.auth;

import com.hb.auth.annotation.model.Auth;
import com.hb.auth.annotation.model.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRequest(
        @Auth.UsernameOrEmail
        @Schema(description = "User's username or email", example = "john_doe_1234 or john@doe.com")
        String username,
        @User.Password
        @Schema(description = "User's password", example = "Azer123&")
        String password) {
}
