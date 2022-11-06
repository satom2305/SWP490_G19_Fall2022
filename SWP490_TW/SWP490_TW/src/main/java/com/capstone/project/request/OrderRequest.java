package com.capstone.project.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {
    private int orderId;
    private int userId;
    private Double totalPrice;
    private String note;
    private int orderStatusId;
    private Date date;
    private int promotionId;
    private String address;
    private String city;
    private String district;
    private String wards;
    private int phoneNumber;
}
