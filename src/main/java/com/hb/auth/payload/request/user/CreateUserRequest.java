package com.hb.auth.payload.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hb.auth.payload.request.BaseRequestDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

/*public record CreateUserRequest(@JsonProperty("first_name")
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
    @Override
    public String firstName() {
        return firstName.toLowerCase();
    }

    @Override
    public String lastName() {
        return lastName.toLowerCase();
    }

    @Override
    public String email() {
        return email.toLowerCase();
    }
}*/
@NoArgsConstructor
@AllArgsConstructor
@Setter
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
   /* @NotBlank(message = "this field should not be blanc")
    @Size(min = 3, max = 50, message = "the size must be between 3 and 128")
    @JsonProperty("username")
    private String username;*/
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

//    public String getUsername() {
//        return username.toLowerCase();
//    }

    public String getEmail() {
        return email.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

}