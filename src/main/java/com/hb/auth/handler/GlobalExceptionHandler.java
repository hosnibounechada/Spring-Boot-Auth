package com.hb.auth.handler;

import com.hb.auth.constant.DatabaseErrorCode;
import com.hb.auth.error.ErrorModel;
import com.hb.auth.error.ErrorResponse;
import com.hb.auth.exception.*;
import com.hb.auth.payload.response.BadRequestErrorResponse;
import com.twilio.exception.ApiException;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.mail.AuthenticationFailedException;
import jakarta.validation.ConstraintViolationException;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.hb.auth.util.StringUtils.*;

@RestControllerAdvice
@Hidden
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(HttpMessageNotReadableException e) {
        return new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleException(InvalidCredentialsException e) {
        return new ErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException e) {
        return new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GoneException.class)
    @ResponseStatus(HttpStatus.GONE)
    public ErrorResponse handleGoneException(GoneException e) {
        return new ErrorResponse(e.getMessage(), HttpStatus.GONE);
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflictException(ConflictException e) {
        return new ErrorResponse(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ObjectNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BadRequestErrorResponse handleObjectNotValidException(ObjectNotValidException e) {
        return new BadRequestErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString(), e.getErrors());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleEResourceAlreadyExistsException(ResourceAlreadyExistsException e) {
        return new ErrorResponse(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BadRequestErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<ErrorModel> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new ErrorModel(toSnakeCase(err.getField()), err.getRejectedValue() == null ? "" : err.getRejectedValue().toString(), err.getDefaultMessage()))
                .toList();

        return new BadRequestErrorResponse("Validation failed", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString(), errors);
    }
    @ExceptionHandler(PropertyReferenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BadRequestErrorResponse handlePropertyReferenceException(PropertyReferenceException e) {
        ErrorModel errorModel = new ErrorModel(e.getPropertyName(),e.getPropertyName(),e.getMessage());
        List<ErrorModel> errors = new ArrayList<>();
        errors.add(errorModel);
        return new BadRequestErrorResponse("Parameters validation failed", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString(), errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BadRequestErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
       List<ErrorModel> errors = new ArrayList<>();
        var violations = e.getConstraintViolations();
        violations.forEach(violation -> {
            String errorMessage = violation.getMessage();
            String invalidValue = violation.getInvalidValue().toString();
            String parameterName = toSnakeCase(violation.getPropertyPath().toString().substring(violation.getPropertyPath().toString().indexOf(".") + 1));
            errors.add(new ErrorModel(parameterName, invalidValue, errorMessage));
        });
       return new BadRequestErrorResponse("Validation failed", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString(), errors);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        return new ErrorResponse(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    // Handling this exception is based on the Database provider
    // This exception handler is meant to handle exceptions thrown by the database provider, specifically PostgreSQL
    // Any other database provider will cause the exception to response with Unknown field and value and message
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> handleSQLException(SQLException e) {
        switch (e.getSQLState()) {
            case DatabaseErrorCode.DUPLICATED -> {
                List<ErrorModel> errors = new ArrayList<>();
                // Regular expression pattern to match the key-value pair
                Pattern pattern = Pattern.compile("Key \\((.*?)\\)=\\((.*?)\\)");

                // Create a matcher object for the input string
                Matcher matcher = pattern.matcher(e.getMessage());

                // Check if a match is found
                if (matcher.find()) {
                    // Extract the key and value from the matched string
                    String key = matcher.group(1);
                    String value = matcher.group(2);

                    // Print the extracted key and value
                    ErrorModel errorModel = new ErrorModel(key, value, "Value already exist");
                    errors.add(errorModel);
                } else {
                    ErrorModel errorModel = new ErrorModel("Unknown field", "Unknown value", "Unknown message");
                    errors.add(errorModel);
                }
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(new BadRequestErrorResponse("Already exists", HttpStatus.CONFLICT, HttpStatus.CONFLICT.toString(), errors));
            }
            case DatabaseErrorCode.SERVER -> {
                // Connection error
                return ResponseEntity
                        .status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body(new ErrorResponse("Service in temporary unavailable", HttpStatus.SERVICE_UNAVAILABLE));
            }
            case DatabaseErrorCode.CONSTRAINTS_VIOLATION -> {
                return ResponseEntity
                        .status(HttpStatus.LOCKED)
                        .body(new ErrorResponse("Constraint violation", HttpStatus.LOCKED));
            }
            default -> {
                // Unknown error
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ErrorResponse("Unknown Error, please contact our support team ", HttpStatus.INTERNAL_SERVER_ERROR));
            }
        }
    }

    @ExceptionHandler(ConnectException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorResponse handleConnectException(ConnectException e){
        return new ErrorResponse(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BadRequestErrorResponse handleApiException(ApiException e){
        return new BadRequestErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString());
    }

    @ExceptionHandler(FileSizeLimitExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BadRequestErrorResponse handleFileSizeLimitExceededException(FileSizeLimitExceededException e) {
        return new BadRequestErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString());
    }
    @ExceptionHandler({FileSizeLimitException.class, FileTypeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BadRequestErrorResponse handleFileSizeLimitAndFileTypeExceptions(FileSizeLimitException e) {
        return new BadRequestErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString());
    }
}
