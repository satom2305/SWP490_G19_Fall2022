package com.capstone.project.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PromotionRequest {
    private int promotionId;
    private String promotionCode;
    private float salePercent;
    private int amount;
}
