package com.capstone.project.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionResponse {
    private int promotionId;
    private String promotionCode;
    private float salePercent;
    private int amount;
}
