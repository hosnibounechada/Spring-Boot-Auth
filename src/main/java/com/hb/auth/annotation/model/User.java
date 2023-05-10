package com.hb.auth.annotation.model;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.*;

import java.lang.annotation.*;

public interface User {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @Constraint(validatedBy = {})
    @Documented
    @NotBlank(message = "this field should not be blank")
    @Size(min = 3, max = 50, message = "the size must be between 3 and 50")
    @interface FirstName {
        String message() default "Invalid first name";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @Constraint(validatedBy = {})
    @Documented
    @NotBlank(message = "this field should not be blanc")
    @Size(min = 3, max = 50, message = "the size must be between 3 and 50")
    @interface LastName {
        String message() default "Invalid last name";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @Constraint(validatedBy = {})
    @Documented
    @NotNull(message = "Age is required field")
    @Min(value = 18, message = "Age must be greater than or equal to 18")
    @Max(value = 120, message = "Age must be less than or equal to 120")
    @interface Age {
        String message() default "Invalid age";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @Constraint(validatedBy = {})
    @Documented
    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,16}$", message = "Username must be 3-16 characters long and can only contain letters, numbers, underscores, and hyphens")
    @interface Username {
        String message() default "Invalid username";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @Constraint(validatedBy = {})
    @Documented
    @NotBlank(message = "Email is required")
    @Size(max = 50)
    @jakarta.validation.constraints.Email(message = "must be a syntactically correct email address")
    @interface Email {
        String message() default "Invalid email";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @Constraint(validatedBy = {})
    @Documented
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "(\\+213|0)([567])[0-9]{8}", message = "Invalid Algerian phone number")
    @interface Phone {
        String message() default "Invalid Algerian phone number";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @Constraint(validatedBy = {})
    @Documented
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long")
    @Pattern.List({
            @Pattern(regexp = "(?=.*[0-9]).+", message = "Password must contain at least one digit"),
            @Pattern(regexp = "(?=.*[a-z]).+", message = "Password must contain at least one lowercase letter"),
            @Pattern(regexp = "(?=.*[A-Z]).+", message = "Password must contain at least one uppercase letter"),
            @Pattern(regexp = "(?=.*[@#$%^&+=]).+", message = "Password must contain at least one special character")
    })
    @interface Password {
        String message() default "Invalid password";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
