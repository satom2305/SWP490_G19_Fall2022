package com.capstone.project.response;

import com.capstone.project.domain.Order;
import com.capstone.project.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailResponse {
    private int order_detail_id;
    private Order order;
    private Product product;
    private String productName;
    private Double productPrice;
    private int quantity;
}
