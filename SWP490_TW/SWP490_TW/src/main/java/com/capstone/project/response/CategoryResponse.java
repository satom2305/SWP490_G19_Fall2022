package com.capstone.project.response;

import com.capstone.project.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
    private int categoryId;
    private String categoryName;
    private List<Product> products;
}
