package com.capstone.project.controller;

import com.capstone.project.response.ProductResponse;
import com.capstone.project.response.UserResponse;
import com.capstone.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("listProduct")
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/rest/products")
    public ResponseEntity<List<ProductResponse>> getAllProductAvailable(){
        return ResponseEntity.ok((productService.getAllProductAvailable()));
    }
}
