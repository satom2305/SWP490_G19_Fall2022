package com.capstone.project.service.impl;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.Category;
import com.capstone.project.domain.Product;
import com.capstone.project.repository.CategoryRepository;
import com.capstone.project.repository.ProductRepository;
import com.capstone.project.request.CategoryRequest;
import com.capstone.project.response.CategoryResponse;
import com.capstone.project.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(Category -> mapper.map(Category, CategoryResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {
        Category category = categoryRepository.save(Category.builder()
                .categoryName(request.getCategoryName())
                .build());
        return mapper.map(category, CategoryResponse.class);
    }

    @Override
    public CategoryResponse update(Integer id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException("Category not found", 404));
        category.setCategoryName(request.getCategoryName());
        categoryRepository.save(category);
        return mapper.map(category, CategoryResponse.class);
    }

    @Override
    public CategoryResponse findById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException("Category not found", 404));
        List<Product> products = productRepository.findByCategory(category);
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .products(products)
                .build();
    }

    @Override
    public void delete(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException("Category not found", 404));
        categoryRepository.delete(category);
    }
}
