package com.hb.auth.payload.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import static com.hb.auth.util.StringUtils.*;

public record UserResponse(
        @Schema(description = "User's id", example = "1")
        Long id,
        @JsonProperty("first_name")
        @Schema(description = "User's first name", example = "John")
        String firstName,
        @JsonProperty("last_name")
        @Schema(description = "User's last name", example = "Doe")
        String lastName,
        @JsonProperty("full_name")
        @Schema(description = "User's full name", example = "John Doe")
        String fullName,
        @Schema(description = "User's age", example = "25")
        Integer age,
        @Schema(description = "User's username", example = "john_doe_1234")
        String username,
        @Schema(description = "User's email", example = "john@doe.com")
        String email,
        @JsonProperty("profile_picture")
        @Schema(description = "User's profile picture", example = "https://www.aws-s3.com/profile_picture")
        String profilePicture,
        @JsonProperty("profile_thumbnail")
        @Schema(description = "User's profile thumbnail", example = "https://www.aws-s3.com/profile_thumbnail")
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
        return firstName() + " " + lastName();
    }

    public String profilePicture() {
        return profilePicture != null ? profilePicture : "default_profile_picture_url";
    }

    public String profileThumbnail() {
        return profileThumbnail != null ? profileThumbnail : "default_profile_thumbnail_url";
    }
}
