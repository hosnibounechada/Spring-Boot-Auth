package com.hb.auth.annotation.swagger.user;

import com.hb.auth.error.ErrorResponse;
import com.hb.auth.payload.response.BadRequestErrorResponse;
import com.hb.auth.payload.response.user.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Delete existing user", description = "Can only be done by already authenticated user, WARNING: user will be deleted permanently with all his history")
@ApiResponses(value = {
        @ApiResponse(description = "Successful operation", responseCode = "204", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))}),
        @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(description = "Conflict", responseCode = "409", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
})
public @interface DeleteUserSwagger {
}
