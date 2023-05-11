package com.hb.auth.payload.response.post;


import io.swagger.v3.oas.annotations.media.Schema;

public record PostResponse(
        @Schema(description = "Post's id", example = "1")
        Long id,
        @Schema(description = "Post's content", example = "Some post content")
        String content) {
}
