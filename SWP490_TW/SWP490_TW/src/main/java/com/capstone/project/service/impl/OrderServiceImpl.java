package com.capstone.project.service.impl;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.Order;
import com.capstone.project.domain.OrderStatus;
import com.capstone.project.domain.Promotion;
import com.capstone.project.domain.User;
import com.capstone.project.repository.*;
import com.capstone.project.request.OrderRequest;
import com.capstone.project.response.OrderResponse;
import com.capstone.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final PromotionRepository promotionRepository;
    private final ModelMapper mapper;

    @Override
    public List<OrderResponse> getAllOrder() {
        return orderRepository.findAll().stream()
                .map(order -> mapper.map(order, OrderResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse create(OrderRequest orderRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new AppException("User not found", 404));
            OrderStatus orderStatus = orderStatusRepository.findById(orderRequest.getOrderStatusId())
                    .orElseThrow(() -> new AppException("Order status not found", 404));
            Promotion promotion = promotionRepository.findById(orderRequest.getPromotionId())
                    .orElseThrow(() -> new AppException("Promotion not found", 404));
            Order order = orderRepository.save(Order.builder()
                            .user(user)
                            .totalPrice(orderRequest.getTotalPrice())
                            .note(orderRequest.getNote())
                            .orderStatus(orderStatus)
                            .date(orderRequest.getDate())
                            .promotion(promotion)
                            .address(orderRequest.getAddress())
                            .city(orderRequest.getCity())
                            .district(orderRequest.getDistrict())
                            .wards(orderRequest.getWards())
                            .phoneNumber(orderRequest.getPhoneNumber())
                    .build());
            return mapper.map(order, OrderResponse.class);
        }else{
            throw new AppException("User not login", 401);
        }
    }

    @Override
    public OrderResponse update(Integer id, OrderRequest orderRequest) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException("Order not found", 404));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof AnonymousAuthenticationToken)){
            String username = auth.getName();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new AppException("User not found", 404));
            order.setUser(user);
            order.setTotalPrice(orderRequest.getTotalPrice());
            order.setNote(orderRequest.getNote());

        }
        return null;
    }

    @Override
    public OrderResponse findById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
