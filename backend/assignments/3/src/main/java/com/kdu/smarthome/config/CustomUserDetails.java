package com.kdu.smarthome.config;

import com.kdu.smarthome.service.UserService;
import com.kdu.smarthome.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * CustomUserDetails class implements UserDetailsService to load user details by username.
 */
@Service
@Slf4j
public class CustomUserDetails implements UserDetailsService {

    private final UserService userService;

    /**
     * Constructor to initialize CustomUserDetails with UserService.
     *
     * @param userService the UserService object
     */
    @Autowired
    public CustomUserDetails(UserService userService) {
        this.userService = userService;
    }

    /**
     * Load user details by username.
     *
     * @param username the username to load details for
     * @return the user details
     * @throws UsernameNotFoundException if user details are not found
     */
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user;
        try {
            user = userService.getUserByUsername(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (user == null) {
            throw new UsernameNotFoundException("User details not found for user: " + username + ". Please register first.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
