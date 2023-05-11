package com.hb.auth.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@Data
public class ErrorResponse {
    @Schema(description = "Message description", example = "Some message description")
    private String message;
    @JsonProperty("status_code")
    @Schema(description = "Status code name", example = "STATUS_NAME")
    private HttpStatusCode statusCode;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
