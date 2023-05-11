package com.hb.auth.payload.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hb.auth.annotation.model.User;
import com.hb.auth.payload.request.BaseRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class CreateUserRequest extends BaseRequestDTO {
    @User.FirstName
    @JsonProperty("first_name")
    @Schema(description = "User's first name", example = "John")
    private String firstName;

    @User.LastName
    @JsonProperty("last_name")
    @Schema(description = "User's last name", example = "Doe")
    private String lastName;

    @User.Age
    @Schema(description = "User's age", example = "25")
    private Integer age;

    @User.Email
    @Schema(description = "User's email", example = "john@doe.com")
    private String email;

    @User.Password
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(description = "User's password", example = "Azer123&")
    private String password;

    public String getFirstName() {
        return firstName.toLowerCase();
    }

    public String getLastName() {
        return lastName.toLowerCase();
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

}