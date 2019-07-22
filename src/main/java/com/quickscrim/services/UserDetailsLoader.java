package com.quickscrim.services;

import com.quickscrim.models.User;
import com.quickscrim.models.UserWithRoles;
import com.quickscrim.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UserRepository users;

    public UserDetailsLoader(UserRepository user) { this.users = user; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);
        if (user ==  null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }
        return new UserWithRoles(user);
    }

    public Iterable<User> getAllUsers() {
        return users.findAll();
    }

    public User getUser(long id) {
        return users.findOne(id);
    }

    public User save(User user) {
        return users.save(user);
    }

    public void delete(Long id) {
        users.delete(id);
    }

}
