package com.capstone.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @NotNull
    @Column(name = "product_name")
    private String productName;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "original_price")
    private float originalPrice;

    @NotNull
    @Column(name = "sale_percent")
    private float salePercent;

    @NotNull
    @Column(name = "sell_price")
    private float sellPrice;

    @NotNull
    @Column(name = "amount")
    private int amount;

    @NotNull
    @Column(name = "created_date")
    private Date createdDate;

    @NotNull
    @Column(name = "product_status")
    private int productStatus;

    @NotNull
    @Column(name = "main_img")
    private String mainImg;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<ProductImg> productImg;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Cart> carts;

}
