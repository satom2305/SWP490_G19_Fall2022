package com.capstone.project.service.impl;

import com.capstone.project.request.CartRequest;
import com.capstone.project.response.CartResponse;
import com.capstone.project.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    @Override
    public CartResponse create(CartRequest cartRequest) {
        return null;
    }

    @Override
    public CartResponse update(int id, CartRequest cartRequest) {
        return null;
    }

    @Override
    public List<CartResponse> getCartByUserId(int userId) {
        return null;
    }
}
