package com.capstone.project.controller;

import com.capstone.project.domain.OrderDetail;
import com.capstone.project.request.OrderDetailRequest;
import com.capstone.project.request.OrderRequest;
import com.capstone.project.response.OrderDetailResponse;
import com.capstone.project.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orderDetails")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService detailService;

    @GetMapping()
    public ResponseEntity<List<OrderDetailResponse>> getAddOrderDetail() {
        return ResponseEntity.ok(detailService.getAllOrderDetails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailResponse> getOrderDetailById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(detailService.getById(id));
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<List<OrderDetailResponse>> getByOrderId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(detailService.getByOrderId(id));
    }

    @PostMapping()
    public ResponseEntity<OrderDetailResponse> createOrderDetail(@RequestBody OrderDetailRequest detailRequest) {
        return ResponseEntity.ok(detailService.createOrderDetail(detailRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetailResponse> updateOrderDetail(@PathVariable("id") Integer id, @RequestBody OrderDetailRequest detailRequest) {
        return ResponseEntity.ok(detailService.updateOrderDetail(id,detailRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteOrderDetail(@PathVariable("id") Integer id){
        detailService.delete(id);
    }

}
