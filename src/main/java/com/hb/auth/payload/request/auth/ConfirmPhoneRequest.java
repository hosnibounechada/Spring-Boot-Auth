package com.hb.auth.payload.request.auth;

import com.hb.auth.annotation.model.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record ConfirmPhoneRequest (
        @User.Phone
        @Schema(description = "User's phone number", example = "+213660504030")
        String phone){
}
