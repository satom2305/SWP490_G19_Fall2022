package com.capstone.project.service;

import com.capstone.project.request.OrderDetailRequest;
import com.capstone.project.request.OrderRequest;
import com.capstone.project.response.OrderDetailResponse;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailResponse> getAllOrderDetails();
    OrderDetailResponse getById(Integer id);
    OrderDetailResponse createOrderDetail(OrderDetailRequest detailRequest);
    OrderDetailResponse updateOrderDetail(Integer id ,OrderDetailRequest detailRequest);
    void delete(Integer id);
    List<OrderDetailResponse> getByOrderId(Integer id);
    List<OrderDetailResponse> updateOrderDetailByOrderId(Integer id,List<OrderDetailRequest> requests);
}
