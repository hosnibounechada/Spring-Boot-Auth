package com.hb.auth.payload.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hb.auth.payload.request.BaseRequestDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class CreateUserRequest extends BaseRequestDTO {
    @JsonProperty("first_name")
    @NotBlank(message = "this field should not be blanc")
    @Size(min = 3, max = 50, message = "the size must be between 3 and 50")
    private String firstName;

    @NotBlank(message = "this field should not be blanc")
    @Size(min = 3, max = 50, message = "the size must be between 3 and 50")
    @JsonProperty("last_name")
    private String lastName;
    private Integer age;

    @NotBlank
    @Size(max = 50)
    @Email(message = "must be a syntactically correct email address")
    private String email;

    @NotBlank
    @Size(min = 6, max = 128)
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