package com.capstone.project.repository;

import com.capstone.project.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("Select p from Product p where p.amount > 0 AND p.productStatus = 1")
    List<Product> findProductAvailable();

    @Query("Select p from Product p where p.amount > 0")
    List<Product> findAllProduct();

}
