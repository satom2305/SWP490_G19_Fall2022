package com.capstone.project.repository;

import com.capstone.project.domain.Product;
import com.capstone.project.domain.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductImgRepository extends JpaRepository<ProductImg, Integer> {
    List<ProductImg> findByProduct(Product product);
}
