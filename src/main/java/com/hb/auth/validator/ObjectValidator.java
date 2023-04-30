package com.hb.auth.validator;

import com.hb.auth.error.ErrorModel;
import com.hb.auth.exception.ObjectNotValidException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.hb.auth.util.StringUtils.toSnakeCase;

public class ObjectValidator {
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public static <T> void validate(T object) {

        Set<ConstraintViolation<T>> validations = validator.validate(object);

        if (!validations.isEmpty()) {
            List<ErrorModel> errors = validations
                    .stream()
                    .map(error -> new ErrorModel(toSnakeCase(error.getPropertyPath().toString()), error.getInvalidValue().toString(), error.getMessage()))
                    .collect(Collectors.toList());
            throw new ObjectNotValidException("Validation failed", errors);
        }
    }
}
