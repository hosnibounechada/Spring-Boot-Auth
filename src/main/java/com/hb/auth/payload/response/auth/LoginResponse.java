package com.hb.auth.payload.response.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hb.auth.payload.response.user.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponse(
        @JsonProperty("user")
        UserResponse userResponse,
        @Schema(description = "User jwt", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
        String jwt) {
}
