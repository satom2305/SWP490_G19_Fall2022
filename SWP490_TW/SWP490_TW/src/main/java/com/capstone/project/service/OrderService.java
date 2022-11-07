package com.capstone.project.service;

import com.capstone.project.request.OrderRequest;
import com.capstone.project.response.OrderResponse;

import java.util.List;

public interface OrderService {
        List<OrderResponse> getAllOrder();
        List<OrderResponse> getAllOrderByUserName(String username);
        OrderResponse create(OrderRequest orderRequest);
        OrderResponse update(Integer id ,OrderRequest orderRequest);
        OrderResponse findById(Integer id);
        void delete(Integer id);

}
