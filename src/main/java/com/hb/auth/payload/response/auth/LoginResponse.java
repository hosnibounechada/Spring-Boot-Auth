package com.hb.auth.payload.response.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hb.auth.payload.response.user.UserResponse;

public record LoginResponse(@JsonProperty("user") UserResponse userResponse, String jwt) {
}
