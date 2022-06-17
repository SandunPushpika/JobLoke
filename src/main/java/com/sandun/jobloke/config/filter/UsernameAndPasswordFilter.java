package com.sandun.jobloke.config.filter;

import com.sandun.jobloke.config.utility.TokenUtility;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsernameAndPasswordFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public UsernameAndPasswordFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Authentication authentication = new UsernamePasswordAuthenticationToken(username,password);

        return authenticationManager.authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();

        String access_token = new TokenUtility().generateAccessToken(user);
        String refresh_token = new TokenUtility().generateRefreshToken(user);

        Cookie a_token = new Cookie("access_token",access_token);
        a_token.setSecure(false);
        a_token.setHttpOnly(true);
        a_token.setPath("/");

        Cookie r_token = new Cookie("refresh_token",refresh_token);
        r_token.setSecure(false);
        r_token.setHttpOnly(true);
        r_token.setPath("/");

        response.addCookie(a_token);
        response.addCookie(r_token);
    }
}
