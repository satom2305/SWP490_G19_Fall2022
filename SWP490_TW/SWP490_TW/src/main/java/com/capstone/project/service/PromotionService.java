package com.capstone.project.service;

import com.capstone.project.request.PromotionRequest;
import com.capstone.project.response.PromotionResponse;

import java.util.List;

public interface PromotionService {
    List<PromotionResponse> getAllPromotion();
    PromotionResponse getPromotionByCode(String code);
    Boolean getPromotionById(Integer id);
    PromotionResponse create(PromotionRequest promotionRequest);
    PromotionResponse update(Integer id, PromotionRequest promotionRequest);
    void delete(Integer id);
    boolean checkPromotion(String code);


}
