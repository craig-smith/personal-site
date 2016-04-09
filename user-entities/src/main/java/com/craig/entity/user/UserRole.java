package com.craig.entity.user;


import com.craig.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Craig on 3/6/2016.
 */
@Entity
public class UserRole extends BaseEntity {

    @Column(nullable = false)
    private String role;

    @ManyToOne(optional = false)
    private User user;

    public UserRole() {
    }

    public UserRole(String role, User user) {
        this.role = role;
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "role='" + role + '\'' +
                ", user=" + user +
                '}';
    }

    public enum USER_ROLE {
        ROLE_USER("ROLE_USER"),
        ROLE_ADMIN("ROLE_ADMIN"),
        ROLE_FORUM_ADMIN("ROLE_FORUM_ADMIN");

        private final String role;

        USER_ROLE(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }

    }
}
