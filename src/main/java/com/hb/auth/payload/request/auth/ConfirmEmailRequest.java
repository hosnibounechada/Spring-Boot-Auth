package com.hb.auth.payload.request.auth;

import com.hb.auth.annotation.model.Auth;
import com.hb.auth.annotation.model.User;

public record ConfirmEmailRequest(@User.Email String email,@Auth.SixDigitCode String code) {
}
