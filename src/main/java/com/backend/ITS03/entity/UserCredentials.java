package com.backend.ITS03.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserCredentials implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    private String userType;
    private String userId;
    private String password;
    private boolean loginStatus;

    public UserCredentials() {
    }

    public UserCredentials(String userType, String userId, String password, boolean loginStatus) {
        this.userType = userType;
        this.userId = userId;
        this.password = password;
        this.loginStatus = loginStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "id=" + id +
                ", userType='" + userType + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", loginStatus=" + loginStatus +
                '}';
    }
}
