package com.capstone.project.repository;

import com.capstone.project.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query(value = "select c from Cart c where c.user.userId = :id")
    List<Cart> findCartByUserId(@Param("id") Integer id);
 }
