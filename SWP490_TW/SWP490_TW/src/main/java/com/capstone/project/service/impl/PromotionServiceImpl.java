package com.capstone.project.service.impl;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.Promotion;
import com.capstone.project.repository.PromotionRepository;
import com.capstone.project.request.PromotionRequest;
import com.capstone.project.response.ProductResponse;
import com.capstone.project.response.PromotionResponse;
import com.capstone.project.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final ModelMapper mapper;

    @Override
    public List<PromotionResponse> getAllPromotion() {
        return promotionRepository.findAll().stream()
                .map(promotion -> mapper.map(promotion, PromotionResponse.class))
                .collect(Collectors.toList()) ;
    }

    @Override
    public PromotionResponse getPromotionByCode(String code) {
            Optional<Promotion> promotion = promotionRepository.findByPromotionCode(code);
       return mapper.map(promotion, PromotionResponse.class);
    }

    @Override
    public Boolean getPromotionById(Integer id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new AppException("Promotion not found", 404));
        if(promotion != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPromotion(String code){
        Optional<Promotion> promotion = promotionRepository.findByPromotionCode(code);
        if(promotion.isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public List<PromotionResponse> searchPromotionByCode(String promotionCode) {
        return promotionRepository.searchListPromotionByCode(promotionCode)
                .stream()
                .map(promotion -> mapper.map(promotion, PromotionResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public PromotionResponse createPromotion(PromotionRequest promotionRequest) {
        Promotion promotion = promotionRepository.save(Promotion.builder()
                        .promotionCode(promotionRequest.getPromotionCode())
                        .salePercent(promotionRequest.getSalePercent())
                        .amount(promotionRequest.getAmount())
                .build());
        return mapper.map(promotion,PromotionResponse.class);
    }

    @Override
    public PromotionResponse updatePromotion(Integer id, PromotionRequest promotionRequest) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new AppException("Promotion not found", 404));
        promotion.setPromotionCode(promotionRequest.getPromotionCode());
        promotion.setSalePercent(promotionRequest.getSalePercent());
        promotion.setAmount(promotion.getAmount());
        promotionRepository.save(promotion);
        return mapper.map(promotion,PromotionResponse.class);
    }

    @Override
    public void deletePromotion(Integer id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new AppException("Promotion not found", 404));
        promotionRepository.delete(promotion);
    }
}
