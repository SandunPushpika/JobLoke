package com.sandun.jobloke.config.filter;

import com.sandun.jobloke.config.utility.TokenUtility;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request.getServletPath());
        if(request.getServletPath().equals("/login") || request.getServletPath().equals("/api/v1/token/refresh") || request.getServletPath().equals("/logout")){
            filterChain.doFilter(request,response);
        }else{
            Cookie[] cookies = request.getCookies();

            if(cookies != null){
                System.out.println("length of cookies "+cookies.length);
                Authentication authentication = new TokenUtility().verifyToken(cookies);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request,response);
            }else{
                System.out.println("No Cookies Detected!");
                filterChain.doFilter(request,response);
            }
        }
    }
}
