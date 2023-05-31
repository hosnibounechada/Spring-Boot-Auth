package com.hb.auth.payload.request.auth;

import com.hb.auth.annotation.model.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record EmailAvailabilityRequest(@User.Email
                                       @Schema(description = "User's email", example = "john@doe.com")
                                       String email) {
}
