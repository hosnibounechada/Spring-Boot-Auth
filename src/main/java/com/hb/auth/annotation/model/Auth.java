package com.hb.auth.annotation.model;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.*;

import java.lang.annotation.*;

public interface Auth {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @Constraint(validatedBy = {})
    @Documented
    @NotBlank(message = "Username or Email is required")
    @Size(min = 10, max = 128, message = "Must be Username or Email between 10 to 128 characters")
    @interface UsernameOrEmail {
        String message() default "Invalid username";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @Constraint(validatedBy = {})
    @Documented
    @Pattern(regexp = "[0-9]{6}", message = "Invalid OTP format, must be a 6-digit number")
    @interface SixDigitOTP {
        String message() default "Invalid OTP format, must be a 6-digit number";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @Constraint(validatedBy = {})
    @Documented
    @Pattern(regexp = "[0-9]{6}", message = "Invalid Code format, must be a 6-digit number")
    @interface SixDigitCode {
        String message() default "Invalid Code format, must be a 6-digit number";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
