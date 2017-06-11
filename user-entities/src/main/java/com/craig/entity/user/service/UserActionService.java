package com.craig.entity.user.service;

import com.craig.entity.user.Action;
import com.craig.entity.user.User;
import com.craig.entity.user.UserAction;
import com.craig.entity.user.repositories.UserActionRepository;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by craig on 8/28/16.
 */
@Service
public class UserActionService {

    @Autowired
    private UserActionRepository userActionRepository;

    private SecureRandom secureRandom;

    public UserActionService(){
        secureRandom = new SecureRandom();
    }

    public String setActivateAccount(User user) {
        String token = new BigInteger(130, secureRandom).toString();
        DateTime today = new DateTime();
        DateTime validTill = today.plusDays(1);

        UserAction userAction = new UserAction(Action.Activate, user, token, new Timestamp(validTill.getMillis()));
        userActionRepository.save(userAction);
        return token;
    }

    public UserAction getUserfromToken(String token) {
        return userActionRepository.findByToken(token);
    }

    public void deleteUserAction(UserAction userAction) {
        userActionRepository.delete(userAction);
    }
}
