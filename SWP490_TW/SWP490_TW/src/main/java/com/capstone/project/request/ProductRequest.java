package com.capstone.project.request;

import com.capstone.project.domain.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    private int productId;
    private String productName;
    private String description;
    private float originalPrice;
    private float salePercent;
    private float sellPrice;
    private int amount;
    private Date createdDate;
    private int productStatus;
    private int categoryId;
    private String mainImg;
}
