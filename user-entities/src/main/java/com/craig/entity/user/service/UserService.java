package com.craig.entity.user.service;

import com.craig.entity.user.User;
import com.craig.entity.user.UserRole;
import com.craig.entity.user.event.AccountCreatedEvent;
import com.craig.entity.user.repositories.RoleRepository;
import com.craig.entity.user.repositories.UserRepository;
import com.craig.entity.user.validator.Password;
import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintTarget;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Craig on 3/6/2016.
 */
@Service
@Validated
public class UserService {

    private static Logger logger = Logger.getLogger(UserService.class);

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public User createNewUser(String username, @Password String password, String email) {
        logger.debug("Creating new user with username " + username);
        Set<UserRole> roles = new HashSet<UserRole>();
        String passHash = bCryptPasswordEncoder.encode(password);
        User user = new User(username, passHash, email);
        userRepository.save(user);
        roleRepository.save(new UserRole("ROLE_USER", user));
        applicationEventPublisher.publishEvent(new AccountCreatedEvent(this, user));
        logger.debug("User created" + user.toString());
        return userRepository.findByUserName(username);
    }


    @Transactional
    public User changePassword(User user, @Password String password) {
        logger.debug("Changing password for user: " + user.toString());
        String hashpass = bCryptPasswordEncoder.encode(password);
        user.setPassword(hashpass);
        userRepository.save(user);
        logger.debug("Password changed for: " + user.toString());
        return user;
    }

    @Transactional
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Transactional
    public void updateDetails(User user) {
        userRepository.save(user);
    }

    @Transactional
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
