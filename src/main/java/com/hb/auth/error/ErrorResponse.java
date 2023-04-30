package com.hb.auth.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;

@AllArgsConstructor
@Data
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
}
