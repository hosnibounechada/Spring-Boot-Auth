package com.hb.auth.payload.request.auth;

public record ConfirmEmailRequest(String email, String code) {
}
