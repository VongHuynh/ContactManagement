package com.example.ContactsManagement.config;

import com.example.ContactsManagement.Entity.Account;
import com.example.ContactsManagement.Service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            // get jwt from request
            String jwt = getJwtFromRequest(request);
            if(StringUtils.hasText(jwt) && tokenProvider.isValidToken(jwt)) {
                // get id from jwt
                String username = tokenProvider.getUsernameFromJwt(jwt);
                // get information Account from id
                Account account = accountService.findByUserName(username);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                System.out.println(userDetails.getAuthorities());
                if(account != null) {
                    // N???u ng?????i d??ng h???p l???, set th??ng tin cho Seturity Context
                    UsernamePasswordAuthenticationToken
                            authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }
            }

        }catch (Exception ex) {
            log.error("failed on set user authentication", ex);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        // Ki???m tra xem header Authorization c?? ch???a th??ng tin jwt kh??ng
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
