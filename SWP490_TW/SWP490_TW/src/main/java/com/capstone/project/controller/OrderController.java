package com.capstone.project.controller;

import com.capstone.project.request.OrderRequest;
import com.capstone.project.response.ResponseObject;
import com.capstone.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping()
    public ResponseEntity<?> getAllOrder() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, orderService.getAllOrder()));
    }

    @GetMapping("/orderUser")
    public ResponseEntity<?> getOrderByUsername() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, orderService.getAllOrderByUserName()));
    }

    @PostMapping()
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        if(orderService.createOrder(orderRequest) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Successfully", true, null));
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("not ok", "False", false, null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") Integer id, @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, orderService.updateOrder(id, orderRequest)));
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") Integer id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/searchOrderByUsername/{username}")
    public ResponseEntity<?> searchOrderByUsername(@PathVariable("username") String username) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, orderService.searchOrderByUsername(username))
        );
    }
}
