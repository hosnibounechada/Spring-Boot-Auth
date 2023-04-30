package com.hb.auth.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hb.auth.error.ErrorModel;
import com.hb.auth.error.ErrorResponse;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public class BadRequestErrorResponse extends ErrorResponse {
    @JsonProperty("error_type")
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

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public List<ErrorModel> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorModel> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + this.getMessage() + '\'' +
                ", statusCode=" + this.getStatusCode() +
                ", errorType='" + errorType + '\'' +
                ", errors=" + errors +
                '}';
    }
}
