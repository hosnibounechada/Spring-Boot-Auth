package com.hb.auth.payload.request.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hb.auth.annotation.model.User;

import java.util.Locale;

public record RegisterRequest(
        @User.FirstName
        @JsonProperty("first_name")
        String firstName,
        @User.LastName
        @JsonProperty("last_name")
        String lastName,
        @User.Age
        Integer age,
        @User.Email
        String email,
        @User.Password
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
