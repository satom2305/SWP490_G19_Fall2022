package com.capstone.project.controller;

import com.capstone.project.request.OrderRequest;
import com.capstone.project.response.OrderResponse;
import com.capstone.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping()
    public ResponseEntity<List<OrderResponse>> getAllOrder() {
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @PostMapping()
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.create(orderRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable("id") Integer id, @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.update(id, orderRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") Integer id) {
        orderService.delete(id);
    }
}
