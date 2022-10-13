package com.capstone.project.response;

import com.capstone.project.domain.Product;
import com.capstone.project.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CartResponse {
    private int cartId;
    private Product product;
    private int amount;
    private User user;

}
