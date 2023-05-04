package com.hb.auth.payload.request.auth;

public record VerifyPhoneRequest(String phone, String otp) {
}
