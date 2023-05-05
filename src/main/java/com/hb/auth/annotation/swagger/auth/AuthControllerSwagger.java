package com.hb.auth.annotation.swagger.auth;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Tag(name = "Authentication Controller", description = "Authentication End-Points for new Users and already registered users")
public @interface AuthControllerSwagger {
}
