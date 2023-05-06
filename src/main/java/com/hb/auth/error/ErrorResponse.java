package com.hb.auth.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private String message;
    @JsonProperty("status_code")
    private HttpStatusCode statusCode;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
