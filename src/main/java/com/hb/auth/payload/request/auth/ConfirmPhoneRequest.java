package com.hb.auth.payload.request.auth;

import com.hb.auth.annotation.model.User;

public record ConfirmPhoneRequest (@User.Phone String phone){
}
