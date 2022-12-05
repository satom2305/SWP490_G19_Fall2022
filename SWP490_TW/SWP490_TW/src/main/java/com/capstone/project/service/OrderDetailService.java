package com.capstone.project.service;

import com.capstone.project.request.OrderDetailRequest;
import com.capstone.project.response.OrderDetailResponse;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailResponse> getAllOrderDetails();
    OrderDetailResponse getOrderDetailById(Integer id);
    OrderDetailResponse createOrderDetail(OrderDetailRequest detailRequest);
    OrderDetailResponse updateOrderDetail(Integer id ,OrderDetailRequest detailRequest);
    void deleteOrderDetail(Integer id);
    List<OrderDetailResponse> getOrderDetailByOrderId(Integer id);
    List<OrderDetailResponse> updateOrderDetailByOrderId(Integer id,List<OrderDetailRequest> requests);

    List<?> createListOrderDetail(List<OrderDetailRequest> requests);
}
