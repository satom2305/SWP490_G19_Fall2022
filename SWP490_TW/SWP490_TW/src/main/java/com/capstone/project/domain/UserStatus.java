package com.capstone.project.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "UserStatus")
public class UserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long statusID;

    @Column(name = "UserStatus")
    private String statusName;

    @OneToMany(mappedBy = "statusID", fetch = FetchType.LAZY)
    private List<User> users;

    public UserStatus() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public long getStatusID() {
        return statusID;
    }

    public void setStatusID(long statusID) {
        this.statusID = statusID;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
