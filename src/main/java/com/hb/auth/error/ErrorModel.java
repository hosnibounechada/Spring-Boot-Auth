package com.hb.auth.error;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorModel(@JsonProperty("field_name")
                         String fieldName,
                         @JsonProperty("rejected_value")
                         String rejectedValue,
                         String message) {
}
