package com.capstone.project.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotNull
    @Column(name="user_name")
    private String username;

    @NotNull
    @Column(name="password")
    private String password;

    @NotNull
    @Column(name="email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id",nullable = true)
    @JsonIgnore
    private Role role;

    // cho toi ti
    // no de nhu nay
    @ManyToOne
    @JoinColumn(name = "status_id",nullable = true)
    @JsonIgnore
    private UserStatus status;

    @OneToMany(mappedBy = "userID", fetch = FetchType.LAZY)
    private List<Order> orders;

}
