package com.hb.auth.payload.request.post;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePostRequest(@NotBlank
                                @Size(max = 256)
                                @Schema(description = "Post's content", example = "Some update on post' content")
                                String content) {
}
