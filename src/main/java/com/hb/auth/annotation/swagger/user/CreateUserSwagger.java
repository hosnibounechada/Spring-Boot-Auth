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
@Operation(summary = "Create user", description = "This can only be done by the logged in user.")
@ApiResponses(value = {
        @ApiResponse(description = "successful operation", responseCode = "201", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))}),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestErrorResponse.class))}),
        @ApiResponse(description = "Conflict, already exist", responseCode = "409", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestErrorResponse.class))}),
})
public @interface CreateUserSwagger {
}
