package com.capstone.project.service;

import com.capstone.project.domain.Product;
import com.capstone.project.request.CategoryRequest;
import com.capstone.project.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAllCategory();

    CategoryResponse createCategory(CategoryRequest request);

    CategoryResponse updateCategory(Integer id, CategoryRequest request);

    CategoryResponse findCategoryById(Integer id);

    void deleteCategory(Integer id);

    List<CategoryResponse> searchCategoryByName(String categoryName);

}
