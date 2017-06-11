package com.craig.entity.user.repositories;

import com.craig.entity.user.UserAction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by craig on 8/28/16.
 */
public interface UserActionRepository extends JpaRepository<UserAction, Long> {

    UserAction findByToken(String token);
}
