package com.capstone.project.service.impl;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.Order;
import com.capstone.project.domain.OrderDetail;
import com.capstone.project.domain.Product;
import com.capstone.project.repository.OrderDetailRepository;
import com.capstone.project.repository.OrderRepository;
import com.capstone.project.repository.ProductRepository;
import com.capstone.project.request.OrderDetailRequest;
import com.capstone.project.response.OrderDetailResponse;
import com.capstone.project.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository detailRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;

    @Override
    public List<OrderDetailResponse> getAllOrderDetails() {
        return detailRepository.findAll()
                .stream()
                .map(orderDetail -> mapper.map(orderDetail, OrderDetailResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailResponse getById(Integer id) {
        OrderDetail orderDetail = detailRepository.findById(id)
                .orElseThrow(() -> new AppException("OrderDetail not found", 404));
        return mapper.map(orderDetail, OrderDetailResponse.class);
    }

    @Override
    public OrderDetailResponse createOrderDetail(OrderDetailRequest detailRequest) {
        Order order = orderRepository.findById(detailRequest.getOrderId())
                .orElseThrow(() -> new AppException("Order not found", 404));
        Product product = productRepository.findById(detailRequest.getProductId())
                .orElseThrow(() -> new AppException("Product not found", 404));
        OrderDetail orderDetail = detailRepository.save(OrderDetail.builder()
                .order(order)
                .product(product)
                .productName(product.getProductName())
                .productPrice(product.getOriginalPrice())
                .quantity(detailRequest.getQuantity())
                .build());
        return mapper.map(orderDetail, OrderDetailResponse.class);
    }

    @Override
    public OrderDetailResponse updateOrderDetail(Integer id, OrderDetailRequest detailRequest) {
        Order order = orderRepository.findById(detailRequest.getOrderId())
                .orElseThrow(() -> new AppException("Order not found", 404));
        Product product = productRepository.findById(detailRequest.getProductId())
                .orElseThrow(() -> new AppException("Product not found", 404));
        OrderDetail orderDetail = detailRepository.findById(id)
                .orElseThrow(() -> new AppException("Order detail not found", 404));
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(detailRequest.getQuantity());
        orderDetail.setProductName(detailRequest.getProductName());
        orderDetail.setProductPrice(detailRequest.getProductPrice());
        detailRepository.save(orderDetail);
        return mapper.map(orderDetail, OrderDetailResponse.class);
    }

    @Override
    public List<OrderDetailResponse> updateOrderDetailByOrderId(Integer id, List<OrderDetailRequest> detailRequest) {
        List<OrderDetailRequest> list = detailRequest;
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException("Order not found", 404));
        Double total = 0.0;
        for (OrderDetailRequest c : list) {
            Product product = productRepository.findById(c.getProductId())
                    .orElseThrow(() -> new AppException("Product not found", 404));
            OrderDetail orderDetail = detailRepository.findById(c.getOrder_detail_id())
                    .orElseThrow(() -> new AppException("Order detail not found", 404));
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(c.getQuantity());
            orderDetail.setProductName(c.getProductName());
            orderDetail.setProductPrice(c.getProductPrice());
            total += product.getSellPrice() * orderDetail.getQuantity();
            detailRepository.save(orderDetail);
        }
        order.setTotalPrice(total);
        return detailRepository.findByOrder(order)
                .stream()
                .map(orderDetail -> mapper.map(orderDetail, OrderDetailResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        detailRepository.deleteById(id);
    }

    @Override
    public List<OrderDetailResponse> getByOrderId(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException("Order not found", 404));
        return detailRepository.findByOrder(order)
                .stream()
                .map(orderDetail -> mapper.map(orderDetail, OrderDetailResponse.class))
                .collect(Collectors.toList());
    }
}

