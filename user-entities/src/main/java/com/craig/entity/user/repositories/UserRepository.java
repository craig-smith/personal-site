package com.craig.entity.user.repositories;

import com.craig.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by Craig on 3/6/2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
