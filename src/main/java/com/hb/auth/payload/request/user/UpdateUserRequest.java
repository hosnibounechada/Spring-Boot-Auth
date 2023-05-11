package com.hb.auth.payload.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hb.auth.annotation.model.User;
import com.hb.auth.payload.request.BaseRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UpdateUserRequest extends BaseRequestDTO {
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

    public String getFirstName() {
        return firstName.toLowerCase();
    }


    public String getLastName() {
        return lastName.toLowerCase();
    }


    public Integer getAge() {
        return age;
    }

}
