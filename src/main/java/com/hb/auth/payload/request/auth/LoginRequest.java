package com.hb.auth.payload.request.auth;

import com.hb.auth.annotation.model.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRequest(
        @User.Username
        @Schema(description = "User's username", example = "john_doe_1234")
        String username,
        @User.Password
        @Schema(description = "User's password", example = "Azer123&")
        String password) {
}
