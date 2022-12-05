package com.capstone.project.controller;

import com.capstone.project.request.CartRequest;
import com.capstone.project.response.ResponseObject;
import com.capstone.project.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> getCartByUsername() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, cartService.getCartByUsername()));
    }

    @PostMapping()
    public ResponseEntity<?> addToCart(@RequestBody CartRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, cartService.createCart(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCartByUsername(@PathVariable("id") Integer id,@RequestBody CartRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, cartService.updateCart(id,request)));
    }

    @PutMapping("/updateListCart/{id}")
    public ResponseEntity<?> updateListCart(@RequestBody List<CartRequest> request){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, cartService.updateListCart(request)));
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable("id") Integer id) {
        cartService.deleteCart(id);
    }

    @DeleteMapping()
    public void deleteCartByUser() {
        cartService.deleteCartByUserId();
    }
}
