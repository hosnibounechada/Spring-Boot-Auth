package com.hb.auth.annotation.swagger.user;

import com.hb.auth.payload.response.PageResponse;
import com.hb.auth.payload.response.user.UserResponse;
import com.hb.auth.view.UserViewImp;
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
@Operation(summary = "Search, filter and sort users", description = "Pageable results to search users, default size is set to 10 users per page")
@ApiResponse(description = "Successful operation", responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PageResponse.class, subTypes = {UserViewImp.class}))})

public @interface SearchUsersByPagesSwagger {
}
