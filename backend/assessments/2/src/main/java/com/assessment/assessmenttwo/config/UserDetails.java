package com.assessment.assessmenttwo.config;
import  com.assessment.assessmenttwo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.assessment.assessmenttwo.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;

@Component

public class UserDetails implements UserDetailsService {

    @Autowired
    UserDetails(UserService userService)
    {
        this.userService = userService;
    }
    UserService userService;

    /**
     * Load user details by username.
     *
     * @param  username   the username to load details for
     * @return            the user details
     */
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userService.getUserByUsername(username);
        String personUserName = null;
        String personPassword = null;
        List<GrantedAuthority> authorities = null;

        if(user == null){
            throw new UsernameNotFoundException("UserEntity details not found for user : " + username + ". Please register first.");
        }else{
            personUserName = user.getUsername();
            personPassword = user.getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
        }
        return new User(personUserName, personPassword, authorities);
    }
}
