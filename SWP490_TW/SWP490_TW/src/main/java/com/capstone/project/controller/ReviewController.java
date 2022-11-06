package com.capstone.project.controller;

import com.capstone.project.request.PostRequest;
import com.capstone.project.request.ReviewRequest;
import com.capstone.project.response.PostResponse;
import com.capstone.project.response.ReviewResponse;
import com.capstone.project.service.PostService;
import com.capstone.project.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    //list theo product
    @GetMapping()
    public ResponseEntity<List<ReviewResponse>> getAllReview() {
        return ResponseEntity.ok(reviewService.getAllReview());
    }

    @PostMapping("/createReview")
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest reviewRequest){
        return ResponseEntity.ok(reviewService.createReview(reviewRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> update(@PathVariable("id") Integer id, @RequestBody ReviewRequest reviewRequest) {
        return ResponseEntity.ok(reviewService.updateReview(id, reviewRequest));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        reviewService.deleteReview(id);
    }
}