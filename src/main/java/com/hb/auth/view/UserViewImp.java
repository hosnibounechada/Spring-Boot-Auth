package com.hb.auth.view;

import com.hb.auth.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.hb.auth.util.StringUtils.upperCaseFirstLetter;


public record UserViewImp(Long id,
                          String firstName,
                          String lastName,
                          Integer age,
                          String username,
                          String email) implements UserView {

    public String getFirstName() {
        return upperCaseFirstLetter(firstName);
    }

    public String getLastName() {
        return upperCaseFirstLetter(lastName);
    }
}
