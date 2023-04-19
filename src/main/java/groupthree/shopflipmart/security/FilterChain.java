package groupthree.shopflipmart.security;

import groupthree.shopflipmart.payload.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class FilterChain extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, javax.servlet.FilterChain filterChain) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        boolean authenticated = false;

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    authenticated = true;
                    break;
                }
            }
        }
        if (authenticated){
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("","",new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
//        else{
//            System.out.println("False");
//            resp.sendRedirect("http://localhost:8082/login");
//        }
//        catch (Exception e){
//            System.out.println("Catch");
//            resp.sendRedirect("http://localhost:8082/login");
//        }
        filterChain.doFilter(req,resp);
    }
}
