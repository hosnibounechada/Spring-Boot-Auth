package com.hb.auth.annotation.swagger.user;

import com.hb.auth.error.ErrorResponse;
import com.hb.auth.payload.response.BadRequestErrorResponse;
import com.hb.auth.payload.response.PageResponse;
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
@Operation(summary = "Get all registered users even unconfirmed accounts", description = "Can only be done by Admins, and only in local or env environments, considered as heavy task for the API")
@ApiResponse(description = "Successful operation", responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PageResponse.class, subTypes = {UserResponse.class}))})

public @interface GetAllUsersSwagger {
}
