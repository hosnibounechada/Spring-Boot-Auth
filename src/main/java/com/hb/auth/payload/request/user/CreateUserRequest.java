package com.hb.auth.payload.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hb.auth.payload.request.BaseRequestDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
/*    public String firstName() {
        return firstName.toLowerCase();
    }

    public String lastName() {
        return lastName.toLowerCase();
    }

    public String email() {
        return email.toLowerCase();
    }*/

    public CreateUserRequest() {
    }

    public CreateUserRequest(String firstName, String lastName, Integer age, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName.toLowerCase();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName.toLowerCase();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email.toLowerCase();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUserRequest request = (CreateUserRequest) o;
        return Objects.equals(firstName, request.firstName) && Objects.equals(lastName, request.lastName) && Objects.equals(age, request.age) && Objects.equals(email, request.email) && Objects.equals(password, request.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, email, password);
    }
}