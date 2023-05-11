package com.hb.auth.controller;

import com.hb.auth.annotation.swagger.post.*;
import com.hb.auth.payload.request.post.CreatePostRequest;
import com.hb.auth.payload.request.post.UpdatePostRequest;
import com.hb.auth.payload.response.PageResponse;
import com.hb.auth.payload.response.post.PostResponse;
import com.hb.auth.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${apiPrefix}/posts")
@PostControllerSwagger
@RequiredArgsConstructor
public class PostController implements IController<Long, CreatePostRequest, UpdatePostRequest, PostResponse> {

    private final PostService postService;

    @Override
    @GetAllPostsSwagger
    @GetMapping("/all")
    public ResponseEntity<PageResponse<PostResponse>> getAll() {
        PageResponse<PostResponse> pageResponse = postService.getAll();

        return ResponseEntity.ok(pageResponse);
    }
    @Override
    @GetMapping
    @GetPostsByPagesSwagger
    public ResponseEntity<PageResponse<PostResponse>> getByPages(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size,
                                                                    @RequestParam(defaultValue = "id") String sortBy,
                                                                    @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(postService.getByPages(page, size, sortBy, direction));
    }
    @Override
    @GetMapping("/{id}")
    @GetPostByIdSwagger
    public ResponseEntity<PostResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getById(id));
    }
    @Override
    @PostMapping
    @CreatePostSwagger
    public ResponseEntity<PostResponse> create(@RequestBody CreatePostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.create(request));
    }
    @Override
    @PutMapping("/{id}")
    @UpdatePostSwagger
    public ResponseEntity<PostResponse> update(@PathVariable Long id, @RequestBody UpdatePostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.update(id, request));
    }
    @Override
    @DeleteMapping("/{id}")
    @DeletePostSwagger
    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        postService.delete(id);
        postService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
