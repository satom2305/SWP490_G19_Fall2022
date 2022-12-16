package com.capstone.project.controller;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.*;
import com.capstone.project.repository.OrderDetailRepository;
import com.capstone.project.request.OrderDetailRequest;
import com.capstone.project.response.OrderDetailResponse;
import com.capstone.project.response.UserResponse;
import com.capstone.project.service.impl.OrderDetailServiceImpl;
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
public class OrderDetailControllerTest {
    @InjectMocks
    private OrderDetailServiceImpl detailService;
    @Mock
    private OrderDetailRepository detailRepository;
    @Spy
    private ModelMapper mapper;
    private OrderDetail orderDetail;
    private Order order;
    private Product product;

    @BeforeEach
    public void init() {
        orderDetail = new OrderDetail(1, order,product,"String",11.0,1);
        order = new Order(1, null, 100.0, "test", null, null, null, "string", "string", "string", "string", "12345678");
        product = new Product(1, "Test product", "Test description", 10.0, 10.0, 10.0, 10, null, 1, "test img", null, null, null);
    }

    @Test
    @DisplayName("Test get all order detail")
    public void TestGetAllOrderDetail(){
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(orderDetail);
        when(detailRepository.findAll()).thenReturn(orderDetails);

        List<OrderDetailResponse> actual = detailService.getAllOrderDetails();
        assertEquals(orderDetails.size(),actual.size());
    }

    @Test
    @DisplayName("Test get all order detail fail")
    public void TestGetAllOrderDetailFail(){
        List<OrderDetail> expect = new ArrayList<>();
        expect.add(orderDetail);
        List<OrderDetail> actual = new ArrayList<>();
        actual.add(orderDetail);

        Mockito.when(detailRepository.findAll()).thenThrow(new NullPointerException(""));
        NullPointerException exception = assertThrows(NullPointerException.class, () -> detailService.getAllOrderDetails());
        assertEquals("", exception.getMessage());
    }

    @Test
    @DisplayName("Test get order detail by id")
    public void TestGetOrderDetailById() {
        OrderDetailRequest detailRequest = new OrderDetailRequest(1,1,1,"String",1.0,1);
        Integer id = 1;
        Mockito.when(detailRepository.findById(id)).thenReturn(Optional.ofNullable(orderDetail));
        OrderDetailResponse detailResponse = detailService.getOrderDetailById(detailRequest.getOrder_detail_id());
        Assert.assertEquals(1, detailResponse.getOrder_detail_id());
    }

    @Test
    @DisplayName("Test get order detail by id fail")
    public void TestGetOrderDetailByIdFail() {
        OrderDetailRequest detailRequest = new OrderDetailRequest(1,1,1,"String",1.0,1);
        Integer id = 2;
        Mockito.when(detailRepository.findById(id)).thenThrow(new AppException("Order Detail not found", 404));
        AppException ex = Assert.assertThrows(AppException.class, () -> detailService.getOrderDetailById(detailRequest.getOrder_detail_id()));
        Assert.assertEquals("OrderDetail not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }

    @Test
    @DisplayName("Test update order detail by id")
    public void TestUpdateOrderDetailById() {
        OrderDetailRequest detailRequest = new OrderDetailRequest(1,1,1,"String",1.0,1);
        Integer id = 1;
        Mockito.when(detailRepository.findById(id)).thenReturn(Optional.ofNullable(orderDetail));
        OrderDetailResponse detailResponse = detailService.getOrderDetailById(detailRequest.getOrder_detail_id());
        Assert.assertEquals(1, detailResponse.getOrder_detail_id());
    }

    @Test
    @DisplayName("Test update order detail by id fail")
    public void TestUpdateOrderDetailByIdFail() {
        OrderDetailRequest detailRequest = new OrderDetailRequest(1,1,1,"String",1.0,1);
        Integer id = 2;
        Mockito.when(detailRepository.findById(id)).thenReturn(Optional.ofNullable(orderDetail));
        AppException ex = Assert.assertThrows(AppException.class, () -> detailService.getOrderDetailById(detailRequest.getOrder_detail_id()));
        Assert.assertEquals("OrderDetail not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }

    @Test
    @DisplayName("Test delete order detail by id")
    public void TestDeleteOrderDetailById() {
        OrderDetailRequest detailRequest = new OrderDetailRequest(1,1,1,"String",1.0,1);
        Integer id = 1;
        Mockito.when(detailRepository.findById(id)).thenReturn(Optional.ofNullable(orderDetail));
        detailService.deleteOrderDetail(detailRequest.getOrder_detail_id());
    }

}
