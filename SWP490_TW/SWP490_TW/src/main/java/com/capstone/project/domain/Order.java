package com.capstone.project.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "note")
    private String note;

    @OneToOne
    @JoinColumn(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "date")
    private Date date;

    @OneToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "wards")
    private String wards;

    @Column(name = "phone_number")
    private int phoneNumber;

}
