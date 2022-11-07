package com.capstone.project.response;

import com.capstone.project.domain.OrderStatus;
import com.capstone.project.domain.Promotion;
import com.capstone.project.domain.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private int orderId;
    private User user;
    private Double totalPrice;
    private String note;
    private OrderStatus orderStatus;
    private Date date;
    private Promotion promotion;
    private String address;
    private String city;
    private String district;
    private String wards;
    private String phoneNumber;
}
