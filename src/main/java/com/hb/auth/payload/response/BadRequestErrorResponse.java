package com.hb.auth.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hb.auth.error.ErrorModel;
import com.hb.auth.error.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatusCode;

import java.util.List;

@Setter
@Getter
public class BadRequestErrorResponse extends ErrorResponse {
    @JsonProperty("error_type")
    @Schema(description = "Error Type", example = "BAD REQUEST")
    private String errorType;
    private List<ErrorModel> errors;

    public BadRequestErrorResponse(String message, HttpStatusCode statusCode){
        super(message, statusCode);
    }

    public BadRequestErrorResponse(String message, HttpStatusCode statusCode, String errorType) {
        super(message, statusCode);
        this.errorType = errorType;
    }

    public BadRequestErrorResponse(String message, HttpStatusCode statusCode, String errorType, List<ErrorModel> errors) {
        super(message, statusCode);
        this.errorType = errorType;
        this.errors = errors;
    }
}
