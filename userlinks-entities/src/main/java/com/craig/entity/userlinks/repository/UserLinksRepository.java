package com.craig.entity.userlinks.repository;


import com.craig.entity.userlinks.UserLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Craig on 2/19/2016.
 */
public interface UserLinksRepository extends JpaRepository<UserLink, Long> {

    public List<UserLink> findByAcl(String acl);
}
