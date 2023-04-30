package com.hb.auth.payload.request.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePostRequest(
        @NotBlank
        @Size(max = 256)
        String content,
        @JsonProperty("user_id")
        Long userId
) {
}
