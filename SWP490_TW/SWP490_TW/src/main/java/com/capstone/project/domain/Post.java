package com.capstone.project.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User user;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="image_link_detail")
    private String image_link_detail;

    @Column(name="date")
    private Date date;

    @Column(name="image_link_thumbnail")
    private String image_link_thumbnail;
}