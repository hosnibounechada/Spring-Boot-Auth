package com.hb.auth.payload.request.user;

import com.hb.auth.annotation.model.User;

public record LoginRequest(@User.Username String username, @User.Password String password) {
}
