package com.capstone.project.controller;

import com.capstone.project.request.OrderDetailRequest;
import com.capstone.project.response.ResponseObject;
import com.capstone.project.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> getAddOrderDetail() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, detailService.getAllOrderDetails()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetailById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, detailService.getOrderDetailById(id)));
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> getByOrderId(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, detailService.getOrderDetailByOrderId(id)));
    }

    @PostMapping()
    public ResponseEntity<?> createOrderDetail(@RequestBody OrderDetailRequest detailRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, detailService.createOrderDetail(detailRequest)));
    }
    @PostMapping("/listOrderDetail")
    public ResponseEntity<?> createListOrderDetail(@RequestBody List<OrderDetailRequest> detailRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, detailService.createListOrderDetail(detailRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(@PathVariable("id") Integer id, @RequestBody OrderDetailRequest detailRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, detailService.updateOrderDetail(id, detailRequest)));
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<?> updateOrderDetailByOrderId(@PathVariable("id") Integer id, @RequestBody List<OrderDetailRequest> detailRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, detailService.updateOrderDetailByOrderId(id,detailRequest)));
    }

    @DeleteMapping("/{id}")
    public void deleteOrderDetail(@PathVariable("id") Integer id) {
        detailService.deleteOrderDetail(id);
    }

}
