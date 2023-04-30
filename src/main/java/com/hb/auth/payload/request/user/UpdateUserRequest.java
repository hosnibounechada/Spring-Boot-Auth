package com.hb.auth.payload.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hb.auth.payload.request.BaseRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class UpdateUserRequest extends BaseRequestDTO {
    @JsonProperty("first_name")
    @NotBlank(message = "this field should not be blanc")
    @Size(min = 3, max = 50, message = "the size must be between 3 and 50")
    private String firstName;

    @NotBlank(message = "this field should not be blanc")
    @Size(min = 3, max = 50, message = "the size must be between 3 and 50")
    @JsonProperty("last_name")
    private String lastName;
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
