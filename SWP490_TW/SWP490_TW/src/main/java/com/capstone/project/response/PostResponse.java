package com.capstone.project.response;

import com.capstone.project.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private int postId;
    private int userId;
    private String title;
    private String content;
    private String image_link_detail;
    private Date date;
    private String image_link_thumbnail;
}
