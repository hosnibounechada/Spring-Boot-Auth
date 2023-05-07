package com.hb.auth.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import static com.hb.auth.util.StringUtils.upperCaseFirstLetter;

public interface UserView {
    Long id();
    @JsonProperty("first_name")
    String firstName();
    @JsonProperty("last_name")
    String lastName();
    Integer age();
    @JsonProperty("full_name")
    default String getFullName(){
        return upperCaseFirstLetter(firstName()) +" "+upperCaseFirstLetter(lastName());
    }
    @JsonProperty("profile_picture")
    String profilePicture();
    @JsonProperty("profile_thumbnail")
    String profileThumbnail();
}
