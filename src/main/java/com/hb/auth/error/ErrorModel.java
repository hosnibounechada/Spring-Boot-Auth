package com.hb.auth.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record ErrorModel(
        @JsonProperty("field_name")
        @Schema(description = "Field name", example = "first_name")
        String fieldName,
        @JsonProperty("rejected_value")
        @Schema(description = "Rejected value", example = "A")
        String rejectedValue,
        @Schema(description = "message description", example = "first_name must at least 3 characters length")
        String message) {
}
