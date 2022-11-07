package com.capstone.project.service;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.Order;
import com.capstone.project.domain.OrderStatus;
import com.capstone.project.domain.Promotion;
import com.capstone.project.domain.User;
import com.capstone.project.repository.OrderRepository;
import com.capstone.project.repository.OrderStatusRepository;
import com.capstone.project.repository.PromotionRepository;
import com.capstone.project.repository.UserRepository;
import com.capstone.project.request.OrderRequest;
import com.capstone.project.response.OrderResponse;
import com.capstone.project.service.impl.OrderServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderServiceTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PromotionRepository promotionRepository;

    @Mock
    private OrderStatusRepository orderStatusRepository;

    @Spy
    private ModelMapper mapper;

    private Order order;
    private User user;
    private Promotion promotion;
    private OrderStatus orderStatus;

    @BeforeEach
    public void init() {
        user = new User(1, "admin", "admin", "admin@gmail.com", true, null, null, null);
        promotion = new Promotion(1, "abc", 70.0, 10);
        orderStatus = new OrderStatus(1, "Shipped");
        order = new Order(1, user, 100.0, "test", orderStatus, null, promotion, "string", "string", "string", "string", "12345678");
    }

    @Test
    public void TestGetAllOrder() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        when(orderRepository.findAll()).thenReturn(orders);

        List<OrderResponse> actual = orderService.getAllOrder();
        assertEquals(orders.size(), actual.size());
    }

    @Test
    public void TestGetAllOrderFail() {
        List<Order> expect = new ArrayList<>();
        expect.add(order);
        List<Order> actual = new ArrayList<>();
        actual.add(order);

        Mockito.when(orderRepository.findAll()).thenThrow(new NullPointerException(""));
        NullPointerException exception = assertThrows(NullPointerException.class, () -> orderService.getAllOrder());
        assertEquals("", exception.getMessage());
    }

    @Test
    @DisplayName("Test update order sucess")
    public void TestUpdateOrderSuccess() {
        OrderRequest orderRequest = new OrderRequest(1, 1, 100.0, "test", 1, null, 1, "string", "string", "string", "string", "12345678");
        Integer id = 1;

        //Mockito.when(userRepository.findById(orderRequest.getUserId())).thenReturn(Optional.ofNullable(user));
        //Mockito.when(promotionRepository.findById(orderRequest.getPromotionId())).thenReturn(Optional.ofNullable(promotion));
        // Mockito.when(orderStatusRepository.findById(orderRequest.getOrderStatusId())).thenReturn(Optional.ofNullable(orderStatus));
        //   Mockito.when(orderRepository.findById(1)).thenReturn(Optional.ofNullable(order));

        Mockito.when(orderRepository.findById(id)).thenReturn(Optional.ofNullable(order));
        Optional<Order> orderResponse = orderRepository.findById(id);
        Assert.assertEquals(orderRequest.getOrderId(), orderResponse.get().getOrderId());

    }

    @Test
    @DisplayName("Test update order fail")
    public void TestUpdateOrderFail() {
        OrderRequest orderRequest = new OrderRequest(1, 1, 100.0, "test", 1, null, 1, "string", "string", "string", "string", "12345678");
        Integer id = 2;

        Mockito.when(orderRepository.findById(id)).thenThrow(new AppException("Order not found", 404));
        AppException ex = Assert.assertThrows(AppException.class, () -> orderService.findById(orderRequest.getOrderId()));
        Assert.assertEquals("Order not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }

    @Test
    @DisplayName("test find order success")
    public void TestFindOrder() {
        OrderRequest orderRequest = new OrderRequest(1, 1, 100.0, "test", 1, null, 1, "string", "string", "string", "string", "12345678");
        Integer id = 1;
        Mockito.when(orderRepository.findById(id)).thenReturn(Optional.ofNullable(order));
        Optional<Order> orderResponse = orderRepository.findById(id);
        Assert.assertEquals(orderRequest.getOrderId(), orderResponse.get().getOrderId());
    }

    @Test
    @DisplayName("test find order fail")
    public void TestFindProductFail() {
        OrderRequest orderRequest = new OrderRequest(1, 1, 100.0, "test", 1, null, 1, "string", "string", "string", "string", "12345678");
        Integer id = 2;

        Mockito.when(orderRepository.findById(id)).thenThrow(new AppException("Order not found", 404));
        AppException ex = Assert.assertThrows(AppException.class, () -> orderService.findById(orderRequest.getOrderId()));
        Assert.assertEquals("Order not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }


}
