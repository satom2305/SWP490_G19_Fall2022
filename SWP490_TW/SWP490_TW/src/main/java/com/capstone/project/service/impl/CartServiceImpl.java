package com.capstone.project.service.impl;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.config.security.JwtRequest;
import com.capstone.project.domain.Cart;
import com.capstone.project.domain.Product;
import com.capstone.project.domain.User;
import com.capstone.project.repository.CartRepository;
import com.capstone.project.repository.ProductRepository;
import com.capstone.project.repository.UserRepository;
import com.capstone.project.request.CartRequest;
import com.capstone.project.response.CartResponse;
import com.capstone.project.response.ProductResponse;
import com.capstone.project.service.CartService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ModelMapper mapper;

    @Override
    public CartResponse create(CartRequest cartRequest) {
        Product product = productRepository.findById(cartRequest.getProductId()).orElseThrow(()-> new AppException("Product not found",404));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(()->new AppException("User not found",404));
            Cart cart = cartRepository.save(Cart.builder()
                            .user(user)
                            .product(product)
                            .amount(cartRequest.getAmount())
                    .build());
            product.setAmount(product.getAmount() - cartRequest.getAmount());
            productRepository.save(product);
            return mapper.map(cart,CartResponse.class);
        }else{
            throw new AppException("User not login",401);
        }
    }

    @Override
    public CartResponse update(int id, CartRequest cartRequest) {
        return null;
    }

    @Override
    public List<CartResponse> getCartByUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof AnonymousAuthenticationToken)){
            String username = auth.getName();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(()->new AppException("User not found",404));
            return cartRepository.findByUser(user)
                    .stream()
                    .map(cart -> mapper.map(cart,CartResponse.class))
                    .collect(Collectors.toList());
        }else{
            throw new AppException("User not login",404);
        }

    }
}
