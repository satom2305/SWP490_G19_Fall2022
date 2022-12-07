package com.capstone.project.service;

import com.capstone.project.request.OrderRequest;
import com.capstone.project.response.OrderResponse;

import java.util.List;

public interface OrderService {
        List<OrderResponse> getAllOrder();
        List<OrderResponse> getAllOrderByUserName();
        OrderResponse createOrder(OrderRequest orderRequest);
        OrderResponse updateOrder(Integer id ,OrderRequest orderRequest);
        OrderResponse findOrderById(Integer id);
        void deleteOrder(Integer id);
        List<OrderResponse> searchOrderByUsername(String username);

}
