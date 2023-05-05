package com.hb.auth.payload.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import static com.hb.auth.util.StringUtils.*;

public record UserResponse(Long id,
                           @JsonProperty("first_name")
                           String firstName,
                           @JsonProperty("last_name")
                           String lastName,
                           @JsonProperty("full_name")
                           String fullName,
                           Integer age,
                           String username,
                           String email,
                           @JsonProperty("profile_picture")
                           String profilePicture,
                           @JsonProperty("profile_thumbnail")
                           String profileThumbnail) {
    @Override
    public String firstName() {
        return upperCaseFirstLetter(firstName);
    }

    @Override
    public String lastName() {
        return upperCaseFirstLetter(lastName);
    }

    @Override
    public String fullName() {
        return firstName()+" "+lastName();
    }

    public String profilePicture() {
        return profilePicture != null ? profilePicture : "default_profile_picture_url";
    }

    public String profileThumbnail() {
        return profileThumbnail != null ? profileThumbnail : "default_profile_thumbnail_url";
    }
}
