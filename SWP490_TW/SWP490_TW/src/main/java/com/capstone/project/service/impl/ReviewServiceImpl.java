package com.capstone.project.service.impl;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.Product;
import com.capstone.project.domain.Review;
import com.capstone.project.domain.User;
import com.capstone.project.repository.ProductRepository;
import com.capstone.project.repository.ReviewRepository;
import com.capstone.project.repository.UserRepository;
import com.capstone.project.request.ReviewRequest;
import com.capstone.project.response.ReviewResponse;
import com.capstone.project.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final UserRepository userRepository;

    private final ReviewRepository reviewRepository;

    private final ProductRepository productRepository;

    private final ModelMapper mapper;

    @Override
    public ReviewResponse getReviewById(Integer id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new AppException("Account not found", 404));
        return mapper.map(review, ReviewResponse.class);
    }

    @Override
    public List<ReviewResponse> getAllReview() {
        return reviewRepository.findAll()
                .stream()
                .map(post -> mapper.map(post, ReviewResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewResponse createReview(ReviewRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException("User not found", 404));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException("Product not found", 404));

        Review review = reviewRepository.save(Review.builder()
                .userId(user)
                .productId(product)
                .reviewDetail(request.getReviewDetail())
                .date(request.getDate())
                .build());
        reviewRepository.save(review);
        return mapper.map(review, ReviewResponse.class);
    }

    @Override
    public ReviewResponse updateReview(Integer id, ReviewRequest request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new AppException("Account not found", 404));
        review.setReviewDetail(request.getReviewDetail());
        review.setDate(request.getDate());
        reviewRepository.save(review);
        return mapper.map(review, ReviewResponse.class);
    }

    @Override
    public void deleteReview(Integer id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new AppException("Account not found", 404));
        reviewRepository.delete(review);
    }

}