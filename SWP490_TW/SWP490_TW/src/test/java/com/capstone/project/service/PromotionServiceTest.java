package com.capstone.project.service;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.Category;
import com.capstone.project.domain.Promotion;
import com.capstone.project.repository.PromotionRepository;
import com.capstone.project.request.CategoryRequest;
import com.capstone.project.request.ProductRequest;
import com.capstone.project.request.PromotionRequest;
import com.capstone.project.response.CategoryResponse;
import com.capstone.project.response.ProductResponse;
import com.capstone.project.response.PromotionResponse;
import com.capstone.project.service.impl.PromotionServiceImpl;
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

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PromotionServiceTest {
    @InjectMocks
    private PromotionServiceImpl promotionService;

    @Mock
    private PromotionRepository promotionRepository;

    @Spy
    private ModelMapper mapper;

    private Promotion promotion;

    @BeforeEach
    public void init() {
        promotion = new Promotion(1, "String", 50, 10);
    }

    @Test
    @DisplayName("test create promotion fail")
    public void TestCreateCategoryFail() {
        //set up
        Promotion promotion = new Promotion();
        PromotionRequest promotionRequest = new PromotionRequest(1, "String", 70, 10);

        Mockito.when(promotionRepository.save(promotion)).thenReturn(promotion);

        //execute
        Exception ex = Assert.assertThrows(Exception.class, () -> promotionService.create(promotionRequest));

        //verify
        Assert.assertEquals("source cannot be null", ex.getMessage());
    }

    @Test
    @DisplayName("test update promotion success")
    public void TestUpdatePromotionSuccess() {
        //set up
        PromotionRequest promotionRequest = new PromotionRequest(1, "String", 70, 10);
        Integer id = 1;
        Mockito.when(promotionRepository.findById(promotionRequest.getPromotionId())).thenReturn(Optional.ofNullable(promotion));

        PromotionResponse promotionResponse = promotionService.update(id, promotionRequest);
        Assert.assertEquals("String", promotionResponse.getPromotionCode());
    }

    @Test
    @DisplayName("test update promotion fail")
    public void TestUpdatePromotionFail() {
        //set up
        PromotionRequest promotionRequest = new PromotionRequest(1, "String", 70, 10);
        Integer id = 2;
        Mockito.when(promotionRepository.findById(promotionRequest.getPromotionId())).thenThrow(new AppException("Promotion not found", 404));

        AppException ex = Assert.assertThrows(AppException.class, () -> promotionService.update(id, promotionRequest));

        Assert.assertEquals("Promotion not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }

    @Test
    @DisplayName("test find promotion success")
    public void TestFindPromotion() {
        PromotionRequest promotionRequest = new PromotionRequest(1, "String", 70, 10);
        Integer id = 1;
        Mockito.when(promotionRepository.findById(promotionRequest.getPromotionId())).thenThrow(new AppException("Promotion not found", 404));
        PromotionResponse promotionResponse = promotionService.update(id, promotionRequest);
        Assert.assertEquals("String", promotionResponse.getPromotionCode());
    }

    @Test
    @DisplayName("test find promotion fail")
    public void TestFindPromotionFail() {
        PromotionRequest promotionRequest = new PromotionRequest(1, "String", 70, 10);
        Integer id = 2;
        Mockito.when(promotionRepository.findById(promotionRequest.getPromotionId())).thenThrow(new AppException("Promotion not found", 404));
        AppException ex = Assert.assertThrows(AppException.class, () -> promotionService.getPromotionByCode(promotionRequest.getPromotionCode()));
        Assert.assertEquals("Promotion not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }


}
