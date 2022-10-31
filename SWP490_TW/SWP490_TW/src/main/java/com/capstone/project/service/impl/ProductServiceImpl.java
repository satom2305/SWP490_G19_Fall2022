package com.capstone.project.service.impl;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.Category;
import com.capstone.project.domain.Product;
import com.capstone.project.repository.CategoryRepository;
import com.capstone.project.repository.ProductRepository;
import com.capstone.project.request.ProductRequest;
import com.capstone.project.response.ProductResponse;
import com.capstone.project.service.CategoryService;
import com.capstone.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Override
    public List<ProductResponse> getAllProductAvailable() {
        return productRepository.findProductAvailable()
                .stream()
                .map(product -> mapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepository.findAllProduct()
                .stream()
                .map(product -> mapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse findById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException("Product not found", 404));
        return mapper.map(product, ProductResponse.class);
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException("Category not found", 404));
        Product product = productRepository.save(Product.builder()
                .productName(request.getProductName())
                .description(request.getDescription())
                .originalPrice(request.getOriginalPrice())
                .sellPrice(request.getSellPrice())
                .salePercent(request.getSalePercent())
                .category(category)
                .amount(request.getAmount())
                .createdDate(request.getCreatedDate())
                .productStatus(request.getProductStatus())
                .mainImg(request.getMainImg())
                .build());
        return mapper.map(product, ProductResponse.class);
    }

    @Override
    public ProductResponse update(Integer id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException("Product not found", 404));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException("Category not found", 404));
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setOriginalPrice(request.getOriginalPrice());
        product.setSellPrice(request.getSellPrice());
        product.setSalePercent(request.getSalePercent());
        product.setCategory(category);
        product.setAmount(request.getAmount());
        product.setCreatedDate(request.getCreatedDate());
        product.setProductStatus(request.getProductStatus());
        productRepository.save(product);
        return mapper.map(product, ProductResponse.class);
    }

    @Override
    public ProductResponse disableProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException("Product not found", 404));
        product.setProductStatus(2);
        productRepository.save(product);
        return mapper.map(product, ProductResponse.class);
    }


}
