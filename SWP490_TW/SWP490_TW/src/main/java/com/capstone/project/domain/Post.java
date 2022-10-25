package com.capstone.project.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
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

    @NotNull
    @Column(name="title")
    private String title;

    @NotNull
    @Column(name="content")
    private String content;


    @Column(name="image_link_detail")
    private String image_link_detail;

    @NotNull
    @Column(name="date")
    private Date date;

    @Column(name="image_link_thumbnail")
    private String image_link_thumbnail;
}