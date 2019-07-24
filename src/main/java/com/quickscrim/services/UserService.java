package com.quickscrim.services;

import com.quickscrim.models.User;
import com.quickscrim.repositories.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userDao;

    public UserService(UserRepository userDao) {
        this.userDao = userDao;
    }

    public boolean isLoggedIn() {
        boolean isAnonymousUser = SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken;
        return ! isAnonymousUser;
    }

    public User loggedInUser() {
        if (! isLoggedIn()) {
            return null;
        }
        User sessionUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return userDao.findOne(sessionUser.getId());
    }

    public boolean isOwner(User usersEvent) {
        if (isLoggedIn()) {
            return (usersEvent.getUsername().equals(loggedInUser().getUsername()));
        }
        return false;
    }
}
