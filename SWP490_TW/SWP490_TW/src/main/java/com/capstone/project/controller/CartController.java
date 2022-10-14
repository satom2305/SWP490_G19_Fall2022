package com.capstone.project.controller;

import com.capstone.project.request.CartRequest;
import com.capstone.project.response.CartResponse;
import com.capstone.project.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @GetMapping()
    public ResponseEntity<List<CartResponse>> getCartByUsername(){
        return ResponseEntity.ok(cartService.getCartByUsername());
    }
    @PostMapping()
    public ResponseEntity<CartResponse> addToCart(@RequestBody CartRequest request) {
        return ResponseEntity.ok(cartService.create(request));
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable("id") Integer id){
        cartService.delete(id);
    }
    @DeleteMapping()
    public void deleteCartByUser(){
        cartService.deleteCartByUserId();
    }
}
