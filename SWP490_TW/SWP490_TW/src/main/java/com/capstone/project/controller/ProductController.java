package com.capstone.project.controller;

import com.capstone.project.repository.ProductRepository;
import com.capstone.project.request.ProductRequest;
import com.capstone.project.response.ResponseObject;
import com.capstone.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    @GetMapping("/listAllProduct")
    public ResponseEntity<?> getAllProduct() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, productService.getAllProduct())
        );
    }

    @GetMapping("/listProduct")
    public ResponseEntity<?> getAllProductAvailable() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, productService.getAllProductAvailable())
        );
    }

    @GetMapping("/lastSixProducts")
    public ResponseEntity<?> getLastSixProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, productService.getLastSixProducts())
        );
    }

    @GetMapping("/searchProductByName/{productName}")
    public ResponseEntity<?> searchProductByName(@PathVariable("productName") String productName) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, productService.searchProductByName(productName))
        );
    }

    @GetMapping("/sort/asc")
    public ResponseEntity<?> sortProductASC() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, productService.getAllProductAvailableASC())
        );
    }

    @GetMapping("/sort/desc")
    public ResponseEntity<?> sortProductDESC() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, productService.getAllProductAvailableDES())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(

                new ResponseObject("ok", "Successfully", true, productService.findById(id)));
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, productService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, productService.update(id, request)));
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<?> disableProduct(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, productService.disableProduct(id)));
    }
}
