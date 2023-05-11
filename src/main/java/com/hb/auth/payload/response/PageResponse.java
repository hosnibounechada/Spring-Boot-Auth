package com.hb.auth.payload.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record PageResponse<T>(
        @Schema(description = "Page number", example = "0")
        Integer page,
        @Schema(description = "Page size", example = "10")
        Integer size,
        @Schema(description = "Total pages", example = "15")
        Integer pages,
        @Schema(description = "Total records", example = "150")
        Integer total,
        List<T> content) {
}
