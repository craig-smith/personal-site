package com.craig.entity.user.event;

import com.craig.entity.user.User;
import org.springframework.context.ApplicationEvent;


/**
 * Created by Craig on 3/12/2016.
 */
public class AccountCreatedEvent extends ApplicationEvent {

    private final User user;

    public AccountCreatedEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
