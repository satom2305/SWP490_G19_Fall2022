package com.capstone.project.service.impl;


import com.capstone.project.domain.Promotion;
import com.capstone.project.repository.PromotionRepository;
import com.capstone.project.request.PromotionRequest;
import com.capstone.project.response.PromotionResponse;
import com.capstone.project.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final Mapper mapper;

    @Override
    public List<PromotionResponse> getAllPromotion() {
        return
        null;

    }

    @Override
    public PromotionResponse getPromotionByCode(String code) {

        return null;
    }

    @Override
    public PromotionResponse create(PromotionRequest promotionRequest) {
        return null;
    }

    @Override
    public PromotionResponse update(Integer id, PromotionRequest promotionRequest) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
