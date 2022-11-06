package com.capstone.project.service;

import com.capstone.project.request.PostRequest;
import com.capstone.project.request.ReviewRequest;
import com.capstone.project.response.PostResponse;
import com.capstone.project.response.ReviewResponse;

import java.util.List;

public interface ReviewService {

    ReviewResponse getReviewById(Integer id);

    List<ReviewResponse> getAllReview();

    ReviewResponse createReview(ReviewRequest request);

    ReviewResponse updateReview(Integer id, ReviewRequest request);

    void deleteReview(Integer id);

    List<ReviewResponse> getAllReviewByPid(Integer pid);
}