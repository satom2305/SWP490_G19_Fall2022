package com.capstone.project.controller;

import com.capstone.project.repository.ProductRepository;
import com.capstone.project.request.ProductRequest;
import com.capstone.project.response.ProductResponse;
import com.capstone.project.response.UserResponse;
import com.capstone.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    @GetMapping("/listAllProduct")
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/listProduct")
    public ResponseEntity<List<ProductResponse>> getAllProductAvailable(){
        return ResponseEntity.ok((productService.getAllProductAvailable()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(productService.findById(id));
    }
    @PostMapping()
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request){
        return ResponseEntity.ok(productService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable("id") Integer id,@RequestBody ProductRequest request){
        return ResponseEntity.ok(productService.update(id,request));
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<ProductResponse> disableProduct(@PathVariable("id") Integer id){
        return ResponseEntity.ok(productService.disableProduct(id));
    }
}
