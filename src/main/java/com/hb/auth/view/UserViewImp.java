package com.hb.auth.view;

import static com.hb.auth.util.StringUtils.upperCaseFirstLetter;


public record UserViewImp(Long id,
                          String firstName,
                          String lastName,
                          Integer age,
                          String username,
                          String email,
                          String profilePicture,
                          String profileThumbnail) implements UserView {

    public String firstName() {
        return upperCaseFirstLetter(firstName);
    }

    public String lastName() {
        return upperCaseFirstLetter(lastName);
    }

    public String profilePicture() {
        return profilePicture != null ? profilePicture : "default_profile_picture_url";
    }
    public String profileThumbnail() {
        return profileThumbnail != null ? profileThumbnail : "default_profile_thumbnail_url";
    }
}
