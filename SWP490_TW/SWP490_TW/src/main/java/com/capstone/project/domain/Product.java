package com.capstone.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @NotNull
    @Column(name="product_name")
    private String productName;

    @NotNull
    @Column(name="description")
    private String description;

    @NotNull
    @Column(name="original_price")
    private float originalPrice;

    @NotNull
    @Column(name="sale_percent")
    private float salePercent;

    @NotNull
    @Column(name="sell_price")
    private float sellPrice;

    @NotNull
    @Column(name="amount")
    private String amount;

    @NotNull
    @Column(name="created_date")
    private String createdDate;

    @NotNull
    @Column(name="product_status")
    private String productStatus;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = true)
    @JsonIgnore
    private Category category;

}
