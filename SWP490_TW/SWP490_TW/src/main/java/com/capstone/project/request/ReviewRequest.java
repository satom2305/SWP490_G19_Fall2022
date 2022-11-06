package com.capstone.project.request;

import com.capstone.project.domain.Product;
import com.capstone.project.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewRequest {
    private int review_id;
    private int userId;
    private int productId;
    private String reviewDetail;
    private Date date;
}