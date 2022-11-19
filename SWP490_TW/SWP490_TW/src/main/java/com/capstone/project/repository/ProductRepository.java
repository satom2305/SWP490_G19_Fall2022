package com.capstone.project.repository;

import com.capstone.project.domain.Category;
import com.capstone.project.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("Select p from Product p where p.amount > 0 AND p.productStatus = 1")
    List<Product> findProductAvailable();

    @Query("Select p from Product p where p.amount > 0")
    List<Product> findAllProduct();

    List<Product> findByCategory(Category category);

    @Modifying
    @Query(value = "SELECT * FROM (SELECT * FROM product ORDER BY product_id DESC LIMIT 6) AS sub ORDER BY product_id ASC;",nativeQuery = true)
    @Transactional
    List<Product> findLastSixProducts();

    @Query(value = "SELECT p FROM Product p WHERE p.productName LIKE %:productName%")
    List<Product> searchListProductByName(@Param("productName") String productName);

    @Query("Select p from Product p where p.amount > 0 AND p.productStatus = 1 ORDER BY p.sellPrice ASC")
    List<Product> findByOrderBySellPriceAsc();

    @Query("Select p from Product p where p.amount > 0 AND p.productStatus = 1 ORDER BY p.sellPrice desc ")
    List<Product> findProductAvailableDES();

}
