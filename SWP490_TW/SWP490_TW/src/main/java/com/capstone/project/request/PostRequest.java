package com.capstone.project.request;

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
public class PostRequest {
    private int postId;
    private User user;
    private String title;
    private String content;
    private String image_link_detail;
    private Date date;
    private String image_link_thumbnail;
}
