package com.capstone.project.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartRequest {
    private int cartId;
    private int productId;
    private int amount;
    private int userId;

}
