package com.capstone.project.request;

import com.capstone.project.domain.OrderStatus;
import com.capstone.project.domain.Promotion;
import com.capstone.project.domain.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {
    private int orderId;
    private int userId;
    private float totalPrice;
    private String note;
    private Date date;
    private String address;
    private String city;
    private String district;
    private String wards;
    private int phoneNumber;
    private int promotionId;
    private int orderStatusId;
}
