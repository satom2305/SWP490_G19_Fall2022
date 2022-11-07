package com.capstone.project.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductImgRequest {
    private int productImgId;
    private String productImgUrl;
    private int productId;
}
