package com.hb.auth.payload.request.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Locale;

public record RegisterRequest(
        @JsonProperty("first_name")
        @NotBlank(message = "this field should not be blanc")
        @Size(min = 3, max = 50, message = "the size must be between 3 and 50")

        String firstName,
        @NotBlank(message = "this field should not be blanc")
        @Size(min = 3, max = 50, message = "the size must be between 3 and 50")
        @JsonProperty("last_name")
        String lastName,
        Integer age,
        @NotBlank
        @Size(max = 50)
        @Email(message = "must be a syntactically correct email address")
        String email,
        @NotBlank
        @Size(min = 6, max = 128)
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password) {
    public String firstName() {
        return firstName.toLowerCase(Locale.ROOT);
    }
    public String lastName() {
        return lastName.toLowerCase(Locale.ROOT);
    }
    public String email() {
        return email.toLowerCase(Locale.ROOT);
    }
}
