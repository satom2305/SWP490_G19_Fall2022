package com.capstone.project.repository;

import com.capstone.project.domain.Order;
import com.capstone.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> getOrderByUser(User user);
}
