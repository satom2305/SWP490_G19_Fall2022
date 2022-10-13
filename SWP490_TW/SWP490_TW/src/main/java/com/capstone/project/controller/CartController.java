package com.capstone.project.controller;

import com.capstone.project.response.CartResponse;
import com.capstone.project.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @GetMapping("/listCart")
    public ResponseEntity<List<CartResponse>> getCartByUsername(){
        return ResponseEntity.ok(cartService.getCartByUsername());
    }
}
