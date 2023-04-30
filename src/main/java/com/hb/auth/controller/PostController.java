package com.hb.auth.controller;

import com.hb.auth.payload.request.post.CreatePostRequest;
import com.hb.auth.payload.request.post.UpdatePostRequest;
import com.hb.auth.payload.response.PageResponse;
import com.hb.auth.payload.response.post.PostResponse;
import com.hb.auth.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${apiPrefix}/posts")
@Tag(name = "post", description = "the post API")
public class PostController implements IController<Long, CreatePostRequest, UpdatePostRequest, PostResponse> {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<PageResponse<PostResponse>> getAll() {
        PageResponse<PostResponse> pageResponse = postService.getAll();

        return ResponseEntity.ok(pageResponse);
    }
    @Override
    @GetMapping
    public ResponseEntity<PageResponse<PostResponse>> getByPages(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size,
                                                                    @RequestParam(defaultValue = "id") String sortBy,
                                                                    @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(postService.getByPages(page, size, sortBy, direction));
    }
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getById(id));
    }
    @Override
    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody CreatePostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.create(request));
    }
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> update(@PathVariable Long id, @RequestBody UpdatePostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.update(id, request));
    }
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        postService.delete(id);
        postService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
