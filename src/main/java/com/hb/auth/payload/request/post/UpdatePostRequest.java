package com.hb.auth.payload.request.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePostRequest(@NotBlank
                                @Size(max = 256)
                                String content) {
}
