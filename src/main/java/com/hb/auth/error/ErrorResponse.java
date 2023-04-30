package com.hb.auth.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;

public class ErrorResponse {
    private String message;
    @JsonProperty("status_code")
    private HttpStatusCode statusCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("problem_detail")
    private ProblemDetail body;

    public ErrorResponse(String message) {
        this.message = message;
    }
    public ErrorResponse(String message, HttpStatusCode statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public ErrorResponse(String message, HttpStatusCode statusCode, ProblemDetail body) {
        this.message = message;
        this.statusCode = statusCode;
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public ProblemDetail getBody() {
        return body;
    }

    public void setBody(ProblemDetail body) {
        this.body = body;
    }
}
