/**
 * FleetLog
 * Apr 29, 2019 7:57:31 PM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.configurations.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.deepakdaneva.fleetlog.models.CustomUserDetails;
import com.deepakdaneva.fleetlog.services.CustomUserDetailsService;

public class JwtAuthTokenFilter extends OncePerRequestFilter {

    public static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwtToken = getJwtToken(request);
            if (jwtToken != null && jwtTokenProvider.isValidJwtToken(jwtToken)) {
                long userId = jwtTokenProvider.getUserIdFromJwtToken(jwtToken);

                CustomUserDetails userDetails = userDetailsService.loadUserById(userId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Can not set user authentication");
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }

        return null;
    }
}
