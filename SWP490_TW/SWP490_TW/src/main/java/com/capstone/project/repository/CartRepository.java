package com.capstone.project.repository;

import com.capstone.project.domain.Cart;
import com.capstone.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
   // @Query(value = "select * from Cart c where c.user_id = :id  ",nativeQuery = true)
    List<Cart> findByUser (User user);
 }