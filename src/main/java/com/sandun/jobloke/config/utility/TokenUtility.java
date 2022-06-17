package com.sandun.jobloke.config.utility;

import com.sandun.jobloke.service.AllServices;
import com.sandun.jobloke.user.ApplicationUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.Cookie;
import java.time.LocalDate;
import java.util.*;

public class TokenUtility {
    private final String KEY = "Hi there esjndanjfnajdnanansdnaksndkansdnaskdnasndkndkandk";

    public String generateAccessToken(User user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles",user.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ 5*60*60*1000))
                .signWith(Keys.hmacShaKeyFor(KEY.getBytes()))
                .compact();
    }

    public String generateRefreshToken(User user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusMonths(6)))
                .signWith(Keys.hmacShaKeyFor(KEY.getBytes()))
                .compact();
    }

    public ApplicationUser refreshToken(String refresh_token, AllServices services){
        try{
            Jws<Claims> JwsClaims = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes()))
                    .parseClaimsJws(refresh_token);
            Claims body = JwsClaims.getBody();

            String username = body.getSubject();

            System.out.println("username "+username);

            ApplicationUser user = services.getUser(username);
            System.out.println(user);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This verifies the refresh token using the cookies.
     * @param cookies
     * @param services
     * @return
     */
    public ApplicationUser refreshToken(Cookie[] cookies, AllServices services){
        try{

            Optional<Cookie> a_cookie = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("access_token")).findFirst();

            String refresh_token = a_cookie.orElseThrow(()-> new RuntimeException("Cookie not found!")).getValue();

            Jws<Claims> JwsClaims = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes()))
                    .parseClaimsJws(refresh_token);
            Claims body = JwsClaims.getBody();

            String username = body.getSubject();

            System.out.println("username "+username);

            return services.getUser(username);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Authentication verifyToken(String token){
        try{
            Jws<Claims> JwsClaims = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes()))
                    .parseClaimsJws(token);
            Claims body = JwsClaims.getBody();

            String username = body.getSubject();
            ArrayList<String> roles = (ArrayList<String>) body.get("roles");

            Collection<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
            roles.stream().map(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role)));

            return new UsernamePasswordAuthenticationToken(username,null,grantedAuthorities);

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Authentication verifyToken(Cookie[] cookies){
        try{

            Optional<Cookie> a_cookie = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("access_token")).findFirst();

            String token = a_cookie.orElseThrow(()-> new RuntimeException("Cookie not found!")).getValue();

            Jws<Claims> JwsClaims = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes()))
                    .parseClaimsJws(token);
            Claims body = JwsClaims.getBody();

            String username = body.getSubject();
            ArrayList<String> roles = (ArrayList<String>) body.get("roles");

            Collection<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
            roles.stream().map(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role)));

            return new UsernamePasswordAuthenticationToken(username,null,grantedAuthorities);

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
