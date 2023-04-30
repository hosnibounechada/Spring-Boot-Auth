package com.hb.auth.payload.response.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hb.auth.view.UserViewImp;

public record LoginResponse(
        @JsonProperty("user")
        UserViewImp userViewImp,
        String jwt) { }
