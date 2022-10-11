package com.capstone.project.service;

import com.capstone.project.request.CategoryRequest;
import com.capstone.project.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAll();
    CategoryResponse create(CategoryRequest request);
    CategoryResponse update(Integer id,CategoryRequest request);
    CategoryResponse findById(Integer id);
    void delete(Integer id);
}
