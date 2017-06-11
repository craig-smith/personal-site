package com.craig.entity.user;


import com.craig.entity.base.BaseEntity;
import com.craig.entity.user.validator.Email;
import com.craig.entity.user.validator.Password;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


/**
 * Created by Craig on 3/6/2016.
 */
@Entity
public class User extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String userName;


    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled;


    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private int loginAttempts;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoll;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(nullable = true)
    private List<UserAction> userAction;

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.enabled = false;
        this.loginAttempts = 0;

    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRoll() {
        return userRoll;
    }

    public void setUserRoll(Set<UserRole> userRoll) {
        this.userRoll = userRoll;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public List<UserAction> getUserAction() {
        return userAction;
    }

    public void setUserAction(List<UserAction> userAction) {
        this.userAction = userAction;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", email='" + email + '\'' +
                ", loginAttempts=" + loginAttempts +
                '}';
    }
}
