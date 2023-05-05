package com.hb.auth.annotation.swagger.post;

import com.hb.auth.error.ErrorResponse;
import com.hb.auth.payload.response.PageResponse;
import com.hb.auth.payload.response.post.PostResponse;
import com.hb.auth.payload.response.user.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Get a specific post", description = "Retrieve a specific post by its id")
@ApiResponse(description = "Successful operation", responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PageResponse.class, subTypes = {PostResponse.class}))})
@ApiResponse(description = "Not found", responseCode = "404", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})

public @interface GetPostByIdSwagger {
}
