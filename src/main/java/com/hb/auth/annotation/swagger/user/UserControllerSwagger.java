package com.hb.auth.annotation.swagger.user;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Tag(name = "User Controller", description = "User Controller To Create, Update, Delete User, or Search users or User posts")
public @interface UserControllerSwagger {
}
