package com.hb.auth.payload.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(@NotBlank(message = "this field should not be blanc")
                           @Size(min = 3, max = 50, message = "the size must be between 3 and 128")
                           @JsonProperty("username")
                           String username,
                           @NotBlank
                           @Size(min = 6, max = 128)
                           @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
                           String password) {
}
