package com.hb.auth.payload.request.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hb.auth.annotation.model.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Locale;
public record RegisterRequest(
        @User.FirstName
        @JsonProperty(value = "first_name")
        @Schema(description = "User's first name", example = "John")
        String firstName,
        @User.LastName
        @JsonProperty("last_name")
        @Schema(description = "User's last name", example = "Doe")
        String lastName,
        @User.Age
        @Schema(description = "User's age", example = "25")
        Integer age,
        @User.Email
        @Schema(description = "User's email", example = "john@doe.com")
        String email,
        @User.Password
        @Schema(description = "User's password", example = "Azer123&")
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
