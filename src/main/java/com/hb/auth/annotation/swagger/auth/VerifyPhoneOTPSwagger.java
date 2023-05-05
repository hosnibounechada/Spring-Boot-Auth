package com.hb.auth.annotation.swagger.auth;

import com.hb.auth.payload.response.BadRequestErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(description = "Successful operation", responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))})
@ApiResponse(description = "Bad request", responseCode = "400", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestErrorResponse.class))})
@ApiResponse(description = "Not found, Wrong OTP or already expired", responseCode = "400", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestErrorResponse.class))})

public @interface VerifyPhoneOTPSwagger {
}
