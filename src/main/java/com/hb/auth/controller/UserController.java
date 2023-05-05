package com.hb.auth.controller;

import com.hb.auth.annotation.swagger.user.*;
import com.hb.auth.payload.request.user.CreateUserRequest;
import com.hb.auth.payload.request.user.UpdateUserRequest;
import com.hb.auth.payload.response.PageResponse;
import com.hb.auth.payload.response.user.UserResponse;
import com.hb.auth.service.UserService;
import com.hb.auth.view.PostView;
import com.hb.auth.view.UserViewImp;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.hb.auth.util.StringUtils.toCamelCase;

@RestController
@RequestMapping("${apiPrefix}/users")
@UserControllerSwagger
@Validated
@EnableMethodSecurity
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
    @GetAllUsersSwagger
    @GetMapping("/all")
    public ResponseEntity<PageResponse<UserResponse>> getAll() {
        PageResponse<UserResponse> pageResponse = userService.getAll();

        return ResponseEntity.ok(pageResponse);
    }

    @GetUsersByPagesSwagger
    @GetMapping
    public ResponseEntity<PageResponse<UserResponse>> getByPages(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size,
                                                                 @RequestParam(defaultValue = "id") String sortBy,
                                                                 @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(userService.getByPages(page, size, sortBy, direction));
    }

    @GetUserByIdSwagger
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

    @CreateUserSwagger
    @PostMapping(consumes = {"application/json"})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
    }

    @UpdateUserSwagger
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.update(id, request));
    }

    @DeleteUserSwagger
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
    @SearchUsersByPagesSwagger
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

    @GetUserPostsSwagger
    @GetMapping("/{id:\\d+}/posts")
    public ResponseEntity<PageResponse<PostView>> getUserPosts(
            @PathVariable Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort_by", defaultValue = "id") String[] sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        return ResponseEntity.ok(userService.getUserPosts(id, page, size, toCamelCase(sortBy), direction));
    }

    @UpdateProfilePictureSwagger
    @GetMapping("/upload")
    public ResponseEntity<UserResponse> updateProfilePicture(@RequestParam("file") MultipartFile file) {
        UserResponse user = userService.updateProfilePicture(1L, file.getOriginalFilename() ,file);
        return ResponseEntity.ok(user);
    }
}
