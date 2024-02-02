//package com.assessment.assessmenttwo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.ArrayList;
//import java.util.List;
//
////@Component
//public class CustomAuthenticationManager implements AuthenticationProvider {
//    @Autowired
//    UserService personService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    /**
//     * Authenticates the given authentication.
//     *
//     * @param  authentication  the authentication object to be authenticated
//     * @return                the authenticated authentication object
//     * @throws AuthenticationException if authentication fails
//     */
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String pwd = authentication.getCredentials().toString();
//        UserDTO person = personService.getUserByUsername(username);
//
//        if(person == null){
//            throw new BadCredentialsException("No user registered with this details!");
//        }else{
//            if (passwordEncoder.matches(pwd, person.getPassword())) {
//                return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(person.getRole()));
//            } else {
//                throw new BadCredentialsException("Invalid password!");
//            }
//        }
//    }
//
//    /**
//     * Check if the provided authentication class is supported.
//     *
//     * @param  authentication  the class to be checked for support
//     * @return                true if the authentication class is supported, false otherwise
//     */
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
//    }
//
//    /**
//     * Get the granted authorities based on the role.
//     *
//     * @param  role   the role for which authorities are granted
//     * @return       the list of granted authorities
//     */
//    private List<GrantedAuthority> getGrantedAuthorities(String role) {
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//
//        grantedAuthorities.add(new SimpleGrantedAuthority(role));
//
//        return grantedAuthorities;
//    }
//}
