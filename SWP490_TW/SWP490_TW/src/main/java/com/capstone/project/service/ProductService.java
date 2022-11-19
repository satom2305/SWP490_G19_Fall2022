package com.capstone.project.service;

import com.capstone.project.request.ProductRequest;
import com.capstone.project.response.ProductResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProductAvailable();

    List<ProductResponse> getAllProduct();

    ProductResponse findById(Integer id);

    ProductResponse create(ProductRequest request);

    ProductResponse update(Integer id, ProductRequest request);

    ProductResponse disableProduct(Integer id);
    List<ProductResponse> getLastSixProducts();

    List<ProductResponse> searchProductByName(String productName);

    List<ProductResponse> getAllProductAvailableASC();
    List<ProductResponse> getAllProductAvailableDES();
}
