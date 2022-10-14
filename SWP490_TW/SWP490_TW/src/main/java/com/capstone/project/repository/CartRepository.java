package com.capstone.project.repository;

import com.capstone.project.domain.Cart;
import com.capstone.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    // @Query(value = "select * from Cart c where c.user_id = :id  ",nativeQuery = true)
    List<Cart> findByUser(User user);

    @Transactional
    @Modifying
    @Query(value = "delete from Cart c where c.user.userId = :id")
    void deleteByUser(@Param("id") Integer id);
}