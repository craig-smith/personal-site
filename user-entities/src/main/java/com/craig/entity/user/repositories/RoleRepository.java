package com.craig.entity.user.repositories;

import com.craig.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Craig on 3/6/2016.
 */
public interface RoleRepository extends JpaRepository<UserRole, String> {
}
