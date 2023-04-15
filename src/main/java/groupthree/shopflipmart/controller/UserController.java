package groupthree.shopflipmart.controller;

import groupthree.shopflipmart.payload.ResponseData;
import groupthree.shopflipmart.security.CookieService;
import groupthree.shopflipmart.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;
    @Autowired
    CookieService cookieService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            HttpServletResponse resp,
            HttpServletRequest req,
            @RequestParam String email,
            @RequestParam String password){
        ResponseData data = cookieService.cookieLogin(resp,req,email,password);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(
            HttpServletResponse resp,
            HttpServletRequest req,
            @RequestParam String email){
        ResponseData data = cookieService.cookieLogout(resp,req,email);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
}
