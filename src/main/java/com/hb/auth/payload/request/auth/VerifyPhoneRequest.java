package com.hb.auth.payload.request.auth;

import com.hb.auth.annotation.model.Auth;
import com.hb.auth.annotation.model.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record VerifyPhoneRequest(
        @User.Phone
        @Schema(description = "User's phone number", example = "+213660504030")
        String phone,
        @Auth.SixDigitOTP
        @Schema(description = "6 digit code", example = "123456")
        String otp) {
}
