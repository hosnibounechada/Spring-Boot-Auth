package com.hb.auth.payload.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hb.auth.annotation.model.User;
import com.hb.auth.payload.request.BaseRequestDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UpdateUserRequest extends BaseRequestDTO {
    @User.FirstName
    @JsonProperty("first_name")
    private String firstName;

    @User.LastName
    @JsonProperty("last_name")
    private String lastName;

    @User.Age
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
