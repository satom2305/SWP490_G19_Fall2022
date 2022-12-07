package com.capstone.project.controller;

import com.capstone.project.request.PostRequest;
import com.capstone.project.response.ResponseObject;
import com.capstone.project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<?> getAllPost() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, postService.getALLPost()));
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, postService.createPost(postRequest)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, postService.getPostById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody PostRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, postService.updatePost(id, request)));

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        postService.deletePost(id);
    }

    @GetMapping("/searchPostByTitle/{title}")
    public ResponseEntity<?> searchPostByTitle(@PathVariable("title") String title) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, postService.searchPostByTitle(title))
        );
    }
}