package com.hb.auth.payload.response.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hb.auth.payload.response.user.UserResponse;
import com.hb.auth.view.UserViewImp;

public record LoginResponse(
        @JsonProperty("user")
        UserResponse userResponse,
        String jwt) { }
