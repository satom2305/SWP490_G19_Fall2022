package com.capstone.project.controller;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.Promotion;
import com.capstone.project.repository.PromotionRepository;
import com.capstone.project.request.PromotionRequest;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PromotionControllerTest {
    @InjectMocks
    private PromotionServiceImpl promotionService;

    @Mock
    private PromotionRepository promotionRepository;

    @Spy
    private ModelMapper mapper;

    private Promotion promotion;

    @BeforeEach
    public void init() {
        promotion = new Promotion(1, "String", 50.0, 10);
    }

    @Test
    @DisplayName("Create Promotion")
    public void TestCreatePromotion() {
        PromotionRequest promotionRequest = new PromotionRequest(1, "String", 50.0, 10);
        Mockito.when(promotionRepository.save(Promotion.builder()
                .promotionCode(promotionRequest.getPromotionCode())
                .amount(promotionRequest.getAmount())
                .salePercent(promotionRequest.getSalePercent())
                .build())).thenReturn(promotion);

        PromotionResponse actual = promotionService.createPromotion(promotionRequest);

        Mockito.verify(mapper, Mockito.timeout(1)).map(promotion, PromotionResponse.class);
        Assert.assertEquals(promotion.getPromotionCode(), actual.getPromotionCode());
    }

    @Test
    @DisplayName("Create promotion fail")
    public void TestCreatePromotionFail() {
        Promotion promotion = new Promotion();
        PromotionRequest promotionRequest = new PromotionRequest(1, "String", 50.0, 10);
        Mockito.when(promotionRepository.save(promotion)).thenReturn(promotion);
        Exception ex = Assert.assertThrows(Exception.class, () -> promotionService.createPromotion(promotionRequest));
        Assert.assertEquals("source cannot be null", ex.getMessage());
    }


    @Test
    @DisplayName("Test get All Promotion")
    public void TestGetAllPromotion() {
        List<Promotion> promotions = new ArrayList<>();
        promotions.add(promotion);
        when(promotionRepository.findAll()).thenReturn(promotions);

        List<PromotionResponse> actual = promotionService.getAllPromotion();
        assertEquals(promotions.size(), actual.size());
    }

    @Test
    @DisplayName("Test get All Promotion fail")
    public void TestGetAllPromotionFail() {
        List<Promotion> expect = new ArrayList<>();
        expect.add(promotion);
        List<Promotion> actual = new ArrayList<>();
        actual.add(promotion);

        Mockito.when(promotionRepository.findAll()).thenThrow(new NullPointerException(""));
        NullPointerException exception = assertThrows(NullPointerException.class, () -> promotionService.getAllPromotion());
        assertEquals("", exception.getMessage());
    }

    @Test
    @DisplayName("test create promotion fail")
    public void TestCreateCategoryFail() {
        //set up
        Promotion promotion = new Promotion();
        PromotionRequest promotionRequest = new PromotionRequest(1, "String", 70.0, 10);

        Mockito.when(promotionRepository.save(promotion)).thenReturn(promotion);

        //execute
        Exception ex = Assert.assertThrows(Exception.class, () -> promotionService.createPromotion(promotionRequest));

        //verify
        Assert.assertEquals("source cannot be null", ex.getMessage());
    }

    @Test
    @DisplayName("Get promotion by code")
    public void GetPromotionByCode() {
        String code = "String";
        Mockito.when(promotionRepository.findByPromotionCode(code)).thenReturn(Optional.ofNullable(promotion));
        PromotionResponse promotionResponse = promotionService.getPromotionByCode(code);
        Assert.assertEquals(promotion.getPromotionCode(), promotionResponse.getPromotionCode());
    }

    @Test
    @DisplayName("Get promotion by code fail")
    public void GetPromotionByCodeFail() {
        PromotionRequest promotionRequest = new PromotionRequest(1, "String", 50.0, 10);
        String code = "String";
        Mockito.when(promotionRepository.findByPromotionCode(code)).thenThrow(new AppException("Promotion not found", 404));
        AppException ex = Assert.assertThrows(AppException.class, () -> promotionService.getPromotionByCode(promotionRequest.getPromotionCode()));
        Assert.assertEquals("Promotion not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }


    @Test
    @DisplayName("test update promotion success")
    public void TestUpdatePromotionSuccess() {
        //set up
        PromotionRequest promotionRequest = new PromotionRequest(1, "String", 70.0, 10);
        Integer id = 1;
        Mockito.when(promotionRepository.findById(promotionRequest.getPromotionId())).thenReturn(Optional.ofNullable(promotion));

        PromotionResponse promotionResponse = promotionService.updatePromotion(id, promotionRequest);
        Assert.assertEquals("String", promotionResponse.getPromotionCode());
    }

    @Test
    @DisplayName("test update promotion fail")
    public void TestUpdatePromotionFail() {
        //set up
        PromotionRequest promotionRequest = new PromotionRequest(1, "String", 70.0, 10);
        Integer id = 2;
        Mockito.when(promotionRepository.findById(promotionRequest.getPromotionId())).thenThrow(new AppException("Promotion not found", 404));

        AppException ex = Assert.assertThrows(AppException.class, () -> promotionService.updatePromotion(id, promotionRequest));

        Assert.assertEquals("Promotion not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }

    @Test
    @DisplayName("test find promotion success")
    public void TestFindPromotion() {
        PromotionRequest promotionRequest = new PromotionRequest(1, "String", 70.0, 10);
        Integer id = 1;
        Mockito.when(promotionRepository.findById(promotionRequest.getPromotionId())).thenThrow(new AppException("Promotion not found", 404));
        PromotionResponse promotionResponse = promotionService.updatePromotion(id, promotionRequest);
        Assert.assertEquals("String", promotionResponse.getPromotionCode());
    }

    @Test
    @DisplayName("test find promotion fail")
    public void TestFindPromotionFail() {
        PromotionRequest promotionRequest = new PromotionRequest(1, "String", 70.0, 10);
        Integer id = 2;
        Mockito.when(promotionRepository.findById(promotionRequest.getPromotionId())).thenThrow(new AppException("Promotion not found", 404));
        AppException ex = Assert.assertThrows(AppException.class, () -> promotionService.getPromotionByCode(promotionRequest.getPromotionCode()));
        Assert.assertEquals("Promotion not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }


}
