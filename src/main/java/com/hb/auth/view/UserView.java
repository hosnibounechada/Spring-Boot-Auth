package com.hb.auth.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import static com.hb.auth.util.StringUtils.upperCaseFirstLetter;

public interface UserView {
    public Long id();
    @JsonProperty("first_name")
    String firstName();
    @JsonProperty("last_name")
    String lastName();
    public Integer age();
    @JsonProperty("full_name")
    default String getFullName(){
        return upperCaseFirstLetter(firstName()) +" "+upperCaseFirstLetter(lastName());
    }
}
