package com.capstone.project.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "order_status")
    private OrderStatus orderStatus;

    @NotNull
    @Column(name="total_price")
    private String totalPrice;

    @NotNull
    @Column(name="note")
    private String note;

    @NotNull
    @Column(name="date")
    private String date;

    @OneToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotionId;

}
