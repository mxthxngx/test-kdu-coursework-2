package com.kdu.smarthome.filter;

import com.kdu.smarthome.config.CustomUserDetails;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;

/**
 * Filter class for JWT-based authentication.
 */
@Slf4j
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    CustomUserDetails customUserDetails;

    public static final String JWT_HEADER = "Authorization";

    /**
     * Performs filtering of HTTP request and response.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @param filterChain the filter chain for performing filtering
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwt = getJWTfromRequest(request);
        if (null != jwt ) {
            try {
                log.info(jwt);
                Claims claims = JWTTokenGenerator.validateToken(jwt);
                String username = String.valueOf(claims.get("username"));

                UserDetails customUserDetailsObj = customUserDetails.loadUserByUsername(username);

                log.info(customUserDetailsObj.toString());
                Authentication auth = new UsernamePasswordAuthenticationToken(customUserDetailsObj.getUsername(), null,
                        customUserDetailsObj.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                throw new BadCredentialsException("Invalid Token received!"+e);
            }

        }
        filterChain.doFilter(request, response);
    }

    /**
     * Determines whether the request should not be filtered.
     *
     * @param request the HTTP servlet request
     * @return true if the servlet path equals "/api/v1/auth/**", false otherwise
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/api/v1/auth/**");
    }

    /**
     * Extracts the JWT token from the HTTP request header.
     *
     * @param request the HTTP servlet request
     * @return the JWT token, or null if not found
     */
    private String getJWTfromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(JWT_HEADER);
        if(bearerToken!=null &&  bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else {
            return null;
        }
    }
}
