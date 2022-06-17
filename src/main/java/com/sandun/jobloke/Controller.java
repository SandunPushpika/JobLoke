package com.sandun.jobloke;

import com.sandun.jobloke.config.utility.TokenUtility;
import com.sandun.jobloke.service.AllServices;
import com.sandun.jobloke.user.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping(path = "api/v1")
public class Controller {
    private final AllServices services;

    @Autowired
    public Controller(AllServices services) {
        this.services = services;
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response){
        //String authorizationHeader = request.getHeader("Authorization");
        if(request.getCookies() != null){
            TokenUtility utility = new TokenUtility();
            ApplicationUser user = utility.refreshToken(request.getCookies(),services);

            Collection<SimpleGrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority(user.getAuthority()));

            User user1 = new User(user.getUsername(),"",roles);

            String access_token = utility.generateAccessToken(user1);
            Cookie cookie = new Cookie("access_token",access_token);
            cookie.setHttpOnly(true);
            cookie.setSecure(false);
            cookie.setPath("/");

            response.addCookie(cookie);

        }else{
            throw new RuntimeException("Refresh token is not valid!");
        }
    }

    @GetMapping("getuser")
    public HashMap<String,String> getLoggedInUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        ApplicationUser user = services.getUser(username);

        HashMap<String,String> map = new HashMap();
        map.put("username",username);
        map.put("role",user.getAuthority());
        return map;
    }
}
