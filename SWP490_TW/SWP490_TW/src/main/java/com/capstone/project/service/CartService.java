package com.capstone.project.service;

import com.capstone.project.request.CartRequest;
import com.capstone.project.response.CartResponse;
import com.capstone.project.response.CategoryResponse;

import java.util.List;

public interface CartService {
    CartResponse createCart(CartRequest cartRequest);

    CartResponse updateCart(int id, CartRequest cartRequest);

    List<CartResponse> updateListCart(List<CartRequest> cartRequests);

    List<CartResponse> getCartByUsername();

    void deleteCart(Integer id);

    void deleteCartByUserId();

}
