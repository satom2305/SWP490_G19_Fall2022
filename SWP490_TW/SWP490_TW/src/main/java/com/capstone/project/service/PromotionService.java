package com.capstone.project.service;

import com.capstone.project.request.PromotionRequest;
import com.capstone.project.response.PromotionResponse;

import java.util.List;

public interface PromotionService {
    List<PromotionResponse> getAllPromotion();
    PromotionResponse getPromotionByCode(String code);
    Boolean getPromotionById(Integer id);
    PromotionResponse createPromotion(PromotionRequest promotionRequest);
    PromotionResponse updatePromotion(Integer id, PromotionRequest promotionRequest);
    void deletePromotion(Integer id);
    boolean checkPromotion(String code);

    List<PromotionResponse> searchPromotionByCode(String promotionCode);


}
