package com.capstone.project.domain;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int promotionId;

    @NotNull
    @Column(name = "promotion_code")
    private String promotionCode;

    @NotNull
    @Column(name = "sale_percent")
    private float salePercent;

    @NotNull
    @Column(name = "amount")
    private int amount;


}
