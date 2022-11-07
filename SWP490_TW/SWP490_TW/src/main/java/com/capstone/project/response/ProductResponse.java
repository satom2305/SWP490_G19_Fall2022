package com.capstone.project.response;

import com.capstone.project.domain.ProductImg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private int productId;
    private String productName;
    private String description;
    private Double originalPrice;
    private Double salePercent;
    private Double sellPrice;
    private int amount;
    private Date createdDate;
    private int productStatus;
    private CategoryResponse category;
    private String mainImg;
    private List<ProductImg> productImgs;

}
