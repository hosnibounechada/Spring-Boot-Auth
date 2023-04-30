package com.hb.auth.controller;

import com.hb.auth.error.ErrorResponse;
import com.hb.auth.payload.request.user.CreateUserRequest;
import com.hb.auth.payload.request.user.UpdateUserRequest;
import com.hb.auth.payload.response.BadRequestErrorResponse;
import com.hb.auth.payload.response.PageResponse;
import com.hb.auth.payload.response.user.UserResponse;
import com.hb.auth.service.UserService;
import com.hb.auth.view.PostView;
import com.hb.auth.view.UserViewImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.hb.auth.util.StringUtils.toCamelCase;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "user", description = "the user API")
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Convert all QueryParams to lower case
     *
     * @return String
     */
    /*@InitBinder
    public void initBinder( WebDataBinder dataBinder )
    {
        StringLowerCaseEditor lowerCaseEditor = new StringLowerCaseEditor();
        dataBinder.registerCustomEditor( String.class, lowerCaseEditor );
    }*/
    @GetMapping("/all")
    public ResponseEntity<PageResponse<UserResponse>> getAll() {
        PageResponse<UserResponse> pageResponse = userService.getAll();

        return ResponseEntity.ok(pageResponse);
    }

    @GetMapping
    public ResponseEntity<PageResponse<UserResponse>> getByPages(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size,
                                                                 @RequestParam(defaultValue = "id") String sortBy,
                                                                 @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(userService.getByPages(page, size, sortBy, direction));
    }

    @ApiResponse(description = "successful operation", responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))})
    @ApiResponse(description = "failed operation", responseCode = "404", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<UserResponse> getById(
            @Parameter(name = "id", example = "1")
            @PathVariable("id")
            @Digits(integer = 3, fraction = 0, message = "must be integer 3 numbers length")
            @Min(value = 0, message = "must be at minimum 0")
            @Max(value = 999, message = "must be less than 1000")
            Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @Operation(summary = "Create user", description = "This can only be done by the logged in user.", tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(description = "successful operation", responseCode = "201", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(description = "failed operation (Bad Request)", responseCode = "400", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestErrorResponse.class))}),
    })
    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
    }

    @Operation(summary = "Update user fields Except for ID, EMAIL and PASSWORD", description = "This can only be done by the logged in user.", tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(description = "successful operation", responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(description = "failed operation (Bad Request)", responseCode = "400", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestErrorResponse.class))}),
            @ApiResponse(description = "failed operation (User Not Found)", responseCode = "404", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.update(id, request));
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        userService.delete(id);
        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    // In order to camelCase Form-data we can follow many techniques:
    // 1- Use @InitBuilder
    // 2- Use @RequestParam(value = "snake_case", defaultValue = "")
    // 3- Use Filter
    // Interceptor and Aspect will be checked later
    // In the Search case we can lower case values or let database ignore case
    @GetMapping("/search")
    public ResponseEntity<PageResponse<UserViewImp>> search(
            @RequestParam(value = "first_name", defaultValue = "") String firstName,
            @RequestParam(value = "last_name", defaultValue = "") String lastName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort_by", defaultValue = "firstName, lastName") String[] sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        PageResponse<UserViewImp> response = userService.search(firstName.toLowerCase(), lastName.toLowerCase(), page, size, toCamelCase(sortBy), direction);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id:\\d+}/posts")
    public ResponseEntity<PageResponse<PostView>> getUserPosts(
            @PathVariable Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort_by", defaultValue = "id") String[] sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        return ResponseEntity.ok(userService.getUserPosts(id, page, size, toCamelCase(sortBy), direction));
    }
}
