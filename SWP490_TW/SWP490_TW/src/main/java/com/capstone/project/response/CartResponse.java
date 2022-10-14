package com.capstone.project.response;

import com.capstone.project.domain.Product;
import com.capstone.project.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponse {
    private int cartId;
    private Product product;
    private int quantity;
    private User user;

}
