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
        Product product = productRepository.findById(cartRequest.getProductId())
                .orElseThrow(() -> new AppException("Product not found", 404));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new AppException("User not found", 404));
            List<Cart> carts = cartRepository.findByUser(user);
            for (Cart c: carts) {
                if (cartRequest.getProductId() == c.getProduct().getProductId()) {
                    Cart cart = cartRepository.findById(c.getCartId())
                            .orElseThrow(() -> new AppException("Cart not found", 404));
                    cart.setQuantity(cart.getQuantity() + 1);
                    product.setAmount(product.getAmount() - 1);
                    cartRepository.save(cart);
                    return mapper.map(cart, CartResponse.class);
                }
            }
                    Cart cart = cartRepository.save(Cart.builder()
                            .user(user)
                            .product(product)
                            .quantity(cartRequest.getQuantity())
                            .build());
                    product.setAmount(product.getAmount() - cartRequest.getQuantity());
                    productRepository.save(product);
                    return mapper.map(cart, CartResponse.class);

        } else {
            throw new AppException("User not login", 401);
        }
    }

    @Override
    public CartResponse update(int id, CartRequest cartRequest) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new AppException("Cart not found", 404));
        Product product = productRepository.findById(cartRequest.getProductId())
                .orElseThrow(() -> new AppException("Product not found", 404));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new AppException("User not found", 404));
            cart.setQuantity(cart.getQuantity() + cartRequest.getQuantity());
            cartRepository.save(cart);
            product.setAmount(product.getAmount() - cart.getQuantity());
            Cart cartNew = cartRepository.save(Cart.builder()
                    .user(user)
                    .product(product)
                    .quantity(cartRequest.getQuantity())
                    .build());
            return mapper.map(cartNew, CartResponse.class);
        } else {
            throw new AppException("User not login", 401);
        }
    }

    @Override
    public List<CartResponse> getCartByUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new AppException("User not found", 404));
            return cartRepository.findByUser(user)
                    .stream()
                    .map(cart -> CartResponse.builder()
                            .cartId(cart.getCartId())
                            .user(user)
                            .product(productRepository.findById(cart.getProduct().getProductId()).orElse(null))
                            .quantity(cart.getQuantity())
                            .build())
                    .collect(Collectors.toList());
        } else {
            throw new AppException("User not login", 404);
        }
    }

    @Override
    public void delete(Integer id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new AppException("Cart not found", 404));
        Product product = productRepository.findById(cart.getProduct().getProductId())
                .orElseThrow(() -> new AppException("Product not found", 404));
        product.setAmount(product.getAmount() + cart.getQuantity());
        productRepository.save(product);
        cartRepository.delete(cart);
    }

    @Override
    public void deleteCartByUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("User not found", 404));
        List<CartResponse> carts = cartRepository.findByUser(user)
                .stream()
                .map(cart -> CartResponse.builder()
                        .cartId(cart.getCartId())
                        .user(user)
                        .product(productRepository.findById(cart.getProduct().getProductId()).orElse(null))
                        .quantity(cart.getQuantity())
                        .build())
                .collect(Collectors.toList());
        for (CartResponse c :carts) {
            Product product = productRepository.findById(c.getProduct().getProductId())
                    .orElseThrow(() -> new AppException("User not found", 404));
            product.setAmount(product.getAmount() + c.getQuantity());
            productRepository.save(product);
        }
        cartRepository.deleteByUser(user.getUserId());
    }
}
