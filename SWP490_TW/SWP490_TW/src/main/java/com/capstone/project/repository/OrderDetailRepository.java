package com.capstone.project.repository;

import com.capstone.project.domain.Order;
import com.capstone.project.domain.OrderDetail;
import com.capstone.project.response.OrderDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findByOrder(Order order);
}
