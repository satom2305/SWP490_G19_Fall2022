package com.capstone.project.controller;

import com.capstone.project.request.ReviewRequest;
import com.capstone.project.response.ResponseObject;
import com.capstone.project.response.ReviewResponse;
import com.capstone.project.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping()
    public ResponseEntity<?> getAllReview() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, reviewService.getAllReview()));
    }

    @PostMapping("/createReview")
    public ResponseEntity<?> createReview(@RequestBody ReviewRequest reviewRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, reviewService.createReview(reviewRequest)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, reviewService.getReviewById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody ReviewRequest reviewRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, reviewService.updateReview(id, reviewRequest)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        reviewService.deleteReview(id);
    }
}