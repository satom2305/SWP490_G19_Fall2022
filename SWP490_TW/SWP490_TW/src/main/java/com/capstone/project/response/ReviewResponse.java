package com.capstone.project.response;

import com.capstone.project.domain.Product;
import com.capstone.project.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewResponse {
    private int review_id;
    private User userId;
    private Product productId;
    private String reviewDetail;
    private Date date;
}