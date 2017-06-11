package com.craig.entity.user;

import com.craig.entity.base.BaseEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by craig on 8/28/16.
 */
@Entity
public class UserAction extends BaseEntity{

    @Column(nullable = false)
    private Action action;

    @ManyToOne(optional = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Timestamp validTill;

    public UserAction() {

    }

    public UserAction(Action action, User user, String token, Timestamp validTill) {
        this.action = action;
        this.user = user;
        this.token = token;
        this.validTill = validTill;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getValidTill() {
        return validTill;
    }

    public void setValidTill(Timestamp validTill) {
        this.validTill = validTill;
    }
}
