package com.hb.auth.annotation.swagger.post;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Tag(name = "Post Controller", description = "Post Controller To Create, Update, Delete Post, or Search Posts")
public @interface PostControllerSwagger {
}
