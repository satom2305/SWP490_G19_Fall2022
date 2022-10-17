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

    @NotNull
    @Column(name = "total_price")
    private float totalPrice;

    @NotNull
    @Column(name = "note")
    private String note;

    @OneToOne
    @JoinColumn(name = "order_status")
    private OrderStatus orderStatus;

    @NotNull
    @Column(name = "date")
    private Date date;

    @OneToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "district")
    private String district;

    @NotNull
    @Column(name = "wards")
    private String wards;

    @NotNull
    @Column(name = "phone_number")
    private int phoneNumber;

}
