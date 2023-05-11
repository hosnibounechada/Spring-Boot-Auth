package com.hb.auth.payload.request.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePostRequest(
        @NotBlank
        @Size(max = 256)
        @Schema(description = "Post's content", example = "Some post content")
        String content,
        @JsonProperty("user_id")
        @Schema(description = "User's id", example = "1")
        Long userId
) {
}
