package com.capstone.project.service.impl;

import com.capstone.project.repository.ProductRepository;
import com.capstone.project.response.ProductResponse;
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


}
