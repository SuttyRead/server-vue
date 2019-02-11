package com.ua.sutty.servervue.security.filters;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ua.sutty.servervue.security.UserAuthentication;
import com.ua.sutty.servervue.util.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter implements Filter {

    @Value("${jwt.key}")
    public String secretKey;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtToken jwtToken;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String authorization = req.getHeader("Authorization");
        if (authorization == null) {
            filterChain.doFilter(req, resp);
            return;
        }
        if (authorization.startsWith("Bearer")) {
            authorization = authorization.substring(7);
            if (jwtToken.validateToken(authorization)) {
                DecodedJWT decode = JWT.decode(authorization);
                String username = decode.getClaim("username").asString();
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UserAuthentication userAuthentication = new UserAuthentication(userDetails);
                userAuthentication.setAuthenticated(true);
                SecurityContextHolder.getContext().setAuthentication(userAuthentication);
                filterChain.doFilter(req, resp);
                return;
            }
        }
        filterChain.doFilter(req, resp);
        SecurityContextHolder.clearContext();
    }

}
