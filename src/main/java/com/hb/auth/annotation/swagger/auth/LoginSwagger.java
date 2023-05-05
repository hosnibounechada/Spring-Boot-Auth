package com.hb.auth.annotation.swagger.auth;

import com.hb.auth.error.ErrorResponse;
import com.hb.auth.payload.response.BadRequestErrorResponse;
import com.hb.auth.payload.response.auth.LoginResponse;
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
@Operation(summary = "Login already registered user", description = "User must have already registered and verified their accounts to be able to proceed ")
@ApiResponse(description = "Successful operation", responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))})
@ApiResponse(description = "Bad request", responseCode = "400", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestErrorResponse.class))})
@ApiResponse(description = "Conflict, resource already exists", responseCode = "409", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})

public @interface LoginSwagger {
}
