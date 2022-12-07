package com.capstone.project.service.impl;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.*;
import com.capstone.project.repository.*;
import com.capstone.project.request.CartRequest;
import com.capstone.project.request.OrderRequest;
import com.capstone.project.response.CartResponse;
import com.capstone.project.response.OrderResponse;
import com.capstone.project.response.ProductResponse;
import com.capstone.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final CartRepository cartRepository;
    private final OrderDetailRepository detailRepository;
    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;
    private final ModelMapper mapper;

    @Override
    public List<OrderResponse> getAllOrder() {
        return orderRepository.findAll().stream()
                .map(order -> mapper.map(order, OrderResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> getAllOrderByUserName() {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new AppException("User not found", 404));
//        return orderRepository.getOrderByUser(user).stream()
//                .map(order -> mapper.map(order, OrderResponse.class))
//                .collect(Collectors.toList());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new AppException("User not found", 404));
        return orderRepository.getOrderByUser(user).stream()
                .map(order -> mapper.map(order, OrderResponse.class))
                .collect(Collectors.toList());
        } else {
            throw new AppException("User not login", 404);
        }
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new AppException("User not found", 404));
            OrderStatus orderStatus = orderStatusRepository.findById(orderRequest.getOrderStatusId())
                    .orElseThrow(() -> new AppException("Order status not found", 404));
            Promotion promotion = promotionRepository.findById(orderRequest.getPromotionId())
                    .orElseThrow(() -> new AppException("Promotion not found", 404));
            LocalDate localDate = LocalDate.now();
            Date date = java.sql.Date.valueOf(localDate);

            List<CartResponse> carts = cartRepository.findByUser(user)
                    .stream()
                    .map(cart -> CartResponse.builder()
                            .cartId(cart.getCartId())
                            .user(user)
                            .product(productRepository.findById(cart.getProduct().getProductId()).orElse(null))
                            .quantity(cart.getQuantity())
                            .build())
                    .collect(Collectors.toList());
            for(CartResponse c : carts){
                Product product = productRepository.findById(c.getProduct().getProductId())
                        .orElseThrow(() -> new AppException("Product not found", 404));
                if(product.getAmount() < c.getQuantity()){
                    return null;
                }
            }

            Order order = orderRepository.save(Order.builder()
                    .user(user)
                    .totalPrice(orderRequest.getTotalPrice())
                    .note(orderRequest.getNote())
                    .orderStatus(orderStatus)
                    .date(date)
                    .promotion(promotion)
                    .address(orderRequest.getAddress())
                    .city(orderRequest.getCity())
                    .district(orderRequest.getDistrict())
                    .wards(orderRequest.getWards())
                    .phoneNumber(orderRequest.getPhoneNumber())
                    .build());

            for(CartResponse c : carts){
                OrderDetail orderDetail = detailRepository.save(OrderDetail.builder()
                                .order(order)
                                .product(productRepository.findById(c.getProduct().getProductId()).orElse(null))
                                .productName(c.getProduct().getProductName())
                                .productPrice(c.getProduct().getSellPrice())
                                .quantity(c.getQuantity())
                        .build());
                Product product = productRepository.findById(c.getProduct().getProductId())
                        .orElseThrow(() -> new AppException("Product not found", 404));
                product.setAmount(product.getAmount() - c.getQuantity());
                productRepository.save(product);
                detailRepository.save(orderDetail);
            }
            System.out.println(order);
            cartRepository.deleteByUser(user.getUserId());
            return mapper.map(order, OrderResponse.class);
        } else {
            throw new AppException("User not login", 401);
        }
    }

    @Override
    public OrderResponse updateOrder(Integer id, OrderRequest orderRequest) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException("Order not found", 404));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new AppException("User not found", 404));
            OrderStatus orderStatus = orderStatusRepository.findById(orderRequest.getOrderStatusId())
                    .orElseThrow(() -> new AppException("Order status not found", 404));
            Promotion promotion = promotionRepository.findById(orderRequest.getPromotionId())
                    .orElseThrow(() -> new AppException("Promotion not found", 404));
            LocalDate localDate = LocalDate.now();
            Date date = java.sql.Date.valueOf(localDate);
            order.setUser(user);
            order.setTotalPrice(orderRequest.getTotalPrice());
            order.setNote(orderRequest.getNote());
            order.setOrderStatus(orderStatus);
            order.setDate(date);
            order.setPromotion(promotion);
            order.setAddress(orderRequest.getAddress());
            order.setCity(orderRequest.getCity());
            order.setDistrict(orderRequest.getDistrict());
            order.setWards(orderRequest.getWards());
            order.setPhoneNumber(orderRequest.getPhoneNumber());
            orderRepository.save(order);
            return mapper.map(order, OrderResponse.class);
        } else {
            throw new AppException("User not login", 401);
        }
    }

    @Override
    public OrderResponse findOrderById(Integer id) {
        Order order = orderRepository.findById(id).
                orElseThrow(() -> new AppException("Order not found", 404));
        return mapper.map(order, OrderResponse.class);
    }

    @Override
    public void deleteOrder(Integer id) {
        Order order = orderRepository.findById(id).
                orElseThrow(() -> new AppException("Order not found", 404));
        List<OrderDetail> list = detailRepository.findByOrder(order);
        for (OrderDetail c: list) {
            detailRepository.delete(c);
        }
        orderRepository.delete(order);
    }

    @Override
    public List<OrderResponse> searchOrderByUsername(String username) {
        return orderRepository.searchOrderByUsername(username)
                .stream()
                .map(order -> mapper.map(order, OrderResponse.class))
                .collect(Collectors.toList());
    }
}
