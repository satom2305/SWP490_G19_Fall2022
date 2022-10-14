package com.capstone.project.service;

import com.capstone.project.request.CartRequest;
import com.capstone.project.response.CartResponse;

import java.util.List;

public interface CartService {
    CartResponse create(CartRequest cartRequest);

    CartResponse update(int id, CartRequest cartRequest);

    List<CartResponse> getCartByUsername();

    void delete(Integer id);

    void deleteCartByUserId();


}
