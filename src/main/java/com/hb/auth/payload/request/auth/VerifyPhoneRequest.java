package com.hb.auth.payload.request.auth;

import com.hb.auth.annotation.model.Auth;
import com.hb.auth.annotation.model.User;

public record VerifyPhoneRequest(@User.Phone String phone, @Auth.SixDigitOTP String otp) {
}
