package com.capstone.project.controller;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.Product;
import com.capstone.project.domain.Promotion;
import com.capstone.project.domain.Review;
import com.capstone.project.domain.User;
import com.capstone.project.repository.ReviewRepository;
import com.capstone.project.request.ReviewRequest;
import com.capstone.project.response.PromotionResponse;
import com.capstone.project.response.ReviewResponse;
import com.capstone.project.service.impl.ReviewServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReviewControllerTest {
    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Mock
    private ReviewRepository reviewRepository;
    @Spy
    private ModelMapper mapper;
    private Review review;
    private User user;
    private Product product;

    @BeforeEach
    public void init() {
        review = new Review(1,user,product,"string",null);
        user = new User(1, "admin", "admin", "admin@gmail.com", true, null, null, null);
        product = new Product(1, "Test product", "Test description", 10.0, 10.0, 10.0, 10, null, 1, "test img", null, null, null);
    }

    @Test
    @DisplayName("Test get review by id")
    public void TestGetReviewById() {
        ReviewRequest request = new ReviewRequest(1,1,1,"String",null);
        Integer id = 1;
        Mockito.when(reviewRepository.findById(id)).thenReturn(Optional.ofNullable(review));
        ReviewResponse reviewResponse = reviewService.getReviewById(request.getReview_id());
        Assert.assertEquals(1, reviewResponse.getReview_id());
    }

    @Test
    @DisplayName("Test get review by id fail")
    public void TestGetReviewByIdFail() {
        ReviewRequest request = new ReviewRequest(1,1,1,"String",null);
        Integer id = 2;
        Mockito.when(reviewRepository.findById(id)).thenThrow(new AppException("Review not found", 404));
        AppException ex = Assert.assertThrows(AppException.class, () -> reviewService.getReviewById(request.getReview_id()));
        Assert.assertEquals("Account not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }

    @Test
    @DisplayName("Test get all review")
    public void TestGetAllReview() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(review);
        when(reviewRepository.findAll()).thenReturn(reviews);

        List<ReviewResponse> actual = reviewService.getAllReview();
        assertEquals(reviews.size(), actual.size());
    }

    @Test
    @DisplayName("Test get all review fail")
    public void TestGetAllReviewFail() {
        List<Review> expect = new ArrayList<>();
        expect.add(review);
        List<Review> actual = new ArrayList<>();
        actual.add(review);

        Mockito.when(reviewRepository.findAll()).thenThrow(new NullPointerException(""));
        NullPointerException exception = assertThrows(NullPointerException.class, () -> reviewService.getAllReview());
        assertEquals("", exception.getMessage());
    }


    @Test
    @DisplayName("Test update review")
    public void TestUpdateReview() {
        ReviewRequest request = new ReviewRequest(1,1,1,"String",null);
        Integer id = 1;
        Mockito.when(reviewRepository.findById(id)).thenReturn(Optional.ofNullable(review));
        ReviewResponse actual = reviewService.updateReview(id,request);
        Assert.assertEquals(1, actual.getReview_id());
    }

    @Test
    @DisplayName("Test update review fail")
    public void TestUpdateReviewFail() {
        ReviewRequest request = new ReviewRequest(1,1,1,"String",null);
        Integer id = 2;
        Mockito.when(reviewRepository.findById(id)).thenThrow(new AppException("Review not found", 404));
        AppException ex = Assert.assertThrows(AppException.class, () -> reviewService.updateReview(id, request));
        Assert.assertEquals("Review not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }

    @Test
    @DisplayName("Test delete review")
    public void TestDeleteReview() {
        ReviewRequest request = new ReviewRequest(1,1,1,"String",null);
        Integer id = 1;
        Mockito.when(reviewRepository.findById(request.getReview_id())).thenReturn(Optional.ofNullable(review));
        reviewService.deleteReview(id);
    }

    @Test
    @DisplayName("Test delete review fail")
    public void TestDeleteReviewFail() {
        ReviewRequest request = new ReviewRequest(1,1,1,"String",null);
        Integer id = 2;
        Mockito.when(reviewRepository.findById(id)).thenThrow(new AppException("Review not found", 404));
        AppException ex = Assert.assertThrows(AppException.class, () -> reviewService.deleteReview(request.getReview_id()));
        Assert.assertEquals("Account not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }

    @Test
    @DisplayName("Test create review")
    public void TestCreateReview() {
//        ReviewRequest request = new ReviewRequest(1,1,1,"String",null);
//        Mockito.when(reviewRepository.save(Review.builder()
//                        .review_id(request.getReview_id())
//                        .userId(user)
//                        .productId(product)
//                        .reviewDetail(request.getReviewDetail())
//                        .date(request.getDate())
//                .build())).thenReturn(review);
//
//        ReviewResponse actual = reviewService.createReview(request);
//
//        Mockito.verify(mapper, Mockito.timeout(1)).map(review, Review.class);
//        Assert.assertEquals(review.getReview_id(), actual.getReview_id());
    }
}
