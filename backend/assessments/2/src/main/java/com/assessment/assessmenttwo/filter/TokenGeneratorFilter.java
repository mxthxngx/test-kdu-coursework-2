package com.assessment.assessmenttwo.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TokenGeneratorFilter extends OncePerRequestFilter {
    public static final String JWT_KEY = "jxgEQeXHuPq8VdhhYFNkANdudQ53YUn4";
    public static final String JWT_HEADER = "Authorization";

    /**
     * Overrides the parent class method to perform internal filtering.
     *
     * @param  request       the HTTP request
     * @param  response      the HTTP response
     * @param  filterChain   the filter chain
     * @throws ServletException   if a servlet exception occurs
     * @throws IOException        if an I/O exception occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("here");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder().issuer("kdu").subject("JWT Token")
                    .claim("username", authentication.getName())
                    .claim("roles", populateAuthorities(authentication.getAuthorities()))
                    .issuedAt(new Date())
                    .expiration(new Date((new Date()).getTime() + 30000000))
                    .signWith(key).compact();
            response.setHeader(JWT_HEADER, jwt);
            logger.info("JWT: " +jwt);
        }


        filterChain.doFilter(request, response);
    }

    /**
     * Determines whether the request should not be filtered.
     *
     * @param  request  the HTTP servlet request
     * @return          true if the servlet path is not "/person/login", false otherwise
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !(request.getServletPath().equals("/user/login") || request.getServletPath().equals("/user/register"));
    }

    /**
     * Populates authorities set from a collection of granted authorities and returns 
     * them as a comma-separated string.
     *
     * @param  collection  the collection of granted authorities
     * @return             the comma-separated string of authorities
     */
    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
}