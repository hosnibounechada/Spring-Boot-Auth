package com.hb.auth.payload.response.auth;

import io.swagger.v3.oas.annotations.media.Schema;

public record EmailAvailabilityResponse(
        @Schema(description = "Email availability", example = "true")
        boolean available) {
}
