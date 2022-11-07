package com.capstone.project.controller;

import com.capstone.project.request.PostRequest;
import com.capstone.project.request.UserRequest;
import com.capstone.project.response.PostResponse;
import com.capstone.project.response.ResponseObject;
import com.capstone.project.response.UserResponse;
import com.capstone.project.service.PostService;
import com.capstone.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<?> getAllPost() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true,postService.getALL()));
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, postService.create(postRequest)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true,postService.getPostById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody PostRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true,postService.update(id, request)));

    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        postService.delete(id);
    }
}