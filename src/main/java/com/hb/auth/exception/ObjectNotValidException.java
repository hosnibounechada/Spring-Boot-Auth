package com.hb.auth.exception;

import com.hb.auth.error.ErrorModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ObjectNotValidException extends RuntimeException {
    private final transient List<ErrorModel> errors;
    public ObjectNotValidException(String message) {
        super(message);
        this.errors = null;
    }

    public ObjectNotValidException(String message, List<ErrorModel> errors) {
        super(message);
        this.errors = errors;
    }
}
