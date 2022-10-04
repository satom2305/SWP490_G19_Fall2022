package com.capstone.project.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userID;

}
