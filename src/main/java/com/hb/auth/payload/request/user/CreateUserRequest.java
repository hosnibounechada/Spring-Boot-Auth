package com.hb.auth.payload.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hb.auth.annotation.model.User;
import com.hb.auth.payload.request.BaseRequestDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class CreateUserRequest extends BaseRequestDTO {
    @User.FirstName
    @JsonProperty("first_name")
    private String firstName;

    @User.LastName
    @JsonProperty("last_name")
    private String lastName;

    @User.Age
    private Integer age;

    @User.Email
    private String email;

    @User.Password
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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