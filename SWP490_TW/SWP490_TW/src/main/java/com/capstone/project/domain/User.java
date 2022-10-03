package com.capstone.project.domain;
import com.sun.istack.NotNull;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userID;

    @NotNull
    @Column(name="Username")
    private String username;

    @NotNull
    @Column(name="Password")
    private String password;

    @NotNull
    @Column(name="Email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "roleID")
    private Role roleID;

    @ManyToOne
    @JoinColumn(name = "statusID")
    private UserStatus statusID;

    @OneToMany(mappedBy = "userID", fetch = FetchType.LAZY)
    private List<Order> orders;

    public User() {
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRoleID() {
        return roleID;
    }

    public void setRoleID(Role roleID) {
        this.roleID = roleID;
    }

    public UserStatus getStatusID() {
        return statusID;
    }

    public void setStatusID(UserStatus statusID) {
        this.statusID = statusID;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
