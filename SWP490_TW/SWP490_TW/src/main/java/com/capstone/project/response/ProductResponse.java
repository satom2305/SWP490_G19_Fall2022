package com.capstone.project.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private int productId;
    private String productName;
    private String description;
    private float originalPrice;
    private float salePercent;
    private float sellPrice;
    private int amount;
    private Date createdDate;
    private int productStatus;
    private CategoryResponse category;

}
