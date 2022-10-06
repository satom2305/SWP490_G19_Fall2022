package com.capstone.project.service;

import com.capstone.project.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProductAvailable();

    List<ProductResponse> getAllProduct();
}
