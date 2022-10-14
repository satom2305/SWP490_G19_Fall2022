package com.capstone.project.response;

import com.capstone.project.domain.OrderStatus;
import com.capstone.project.domain.Promotion;
import com.capstone.project.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private int orderId;
    private User user;
    private float totalPrice;
    private String note;
    private Date date;
    private String address;
    private String city;
    private String district;
    private String wards;
    private int phoneNumber;
    private Promotion promotion;
    private OrderStatus orderStatus;
}
