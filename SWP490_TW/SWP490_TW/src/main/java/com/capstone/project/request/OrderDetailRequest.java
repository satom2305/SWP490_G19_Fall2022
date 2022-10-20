package com.capstone.project.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailRequest {

    private int order_detail_id;
    private int orderId;
    private int productId;
    private String productName;
    private float productPrice;
    private int quantity;
}
