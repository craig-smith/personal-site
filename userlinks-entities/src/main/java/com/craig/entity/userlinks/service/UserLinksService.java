package com.craig.entity.userlinks.service;

import com.craig.entity.userlinks.UserLink;
import com.craig.entity.userlinks.repository.UserLinksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Craig on 2/19/2016.
 */
@Service(value = "userLinksService")
public class UserLinksService {

    @Autowired
    UserLinksRepository userLinksRepository;

    public List<UserLink> getUserLinks() {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<UserLink> links = new ArrayList<UserLink>();
        for (SimpleGrantedAuthority authority : authorities) {
        links.addAll(userLinksRepository.findByAcl(authority.getAuthority()));
        }

        return links;
    }

    public UserLink findById(Long id) {
        return userLinksRepository.findOne(id);
    }

    public void deleteById(Long id) {
        userLinksRepository.delete(id);
    }

    public void save(UserLink userLink){
        userLinksRepository.save(userLink);
    }

    public List<UserLink> getAllLinks(){
        return userLinksRepository.findAll();
    }

    }
