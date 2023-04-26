package groupthree.shopflipmart.security;

import groupthree.shopflipmart.common.Constant;
import groupthree.shopflipmart.dto.CartDTO;
import groupthree.shopflipmart.payload.ResponseData;
import groupthree.shopflipmart.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Component
public class CookieService {

    Constant constant = new Constant();

    @Autowired
    UserServiceImp userServiceImp;

    public ResponseData cookieLogin(HttpServletResponse resp, HttpServletRequest req, HttpSession session, String email, String password){

        ResponseData data = new ResponseData();
        Cookie[] cookies = req.getCookies();
        boolean authenticated = false;
        if (cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("username")){
                    authenticated = true;
                    break;
                }
            }
            if (authenticated){
                data.setData(1);
                data.setDesc("Already log in!");
            }else{
                boolean check = userServiceImp.login(email, password);
                if (check) {
                    Cookie cookie = new Cookie("username",email);
                    cookie.setMaxAge(constant.TIME_COOKIES_SESSIONS);
                    resp.addCookie(cookie);
                    data.setData(1);
                    data.setDesc(data.getDesc()+" user had to log in and have logged in successfully");

                    HashMap<Integer, CartDTO> cart = new HashMap<Integer, CartDTO>();
                    session.setAttribute("Cart", cart);
                    session.setMaxInactiveInterval(constant.TIME_COOKIES_SESSIONS);
                }
                else {
                    data.setData(0); // Incorrect email and password, set data = 0
                    data.setDesc(data.getDesc()+" user had to log in and have not logged in successfully");
                }
            }
        }
        else {
            boolean check = userServiceImp.login(email, password);
            if (check) {
                data.setData(1); // Correct email and password, set data = 1
                data.setDesc(data.getDesc()+" cookie expired and user have logged in successfully!");
                Cookie cookie = new Cookie("username",email);
                cookie.setMaxAge(constant.TIME_COOKIES_SESSIONS);
                resp.addCookie(cookie);

                HashMap<Integer, CartDTO> cart = new HashMap<Integer, CartDTO>();
                session.setAttribute("Cart", cart);
                session.setMaxInactiveInterval(constant.TIME_COOKIES_SESSIONS);
            }
            else {
                data.setData(0); // Incorrect email and password, set data = 0
                data.setDesc(data.getDesc()+" cookie expired and user have not logged in successfully!");
            }
        }
        return data;
    }

    public ResponseData cookieLogout(HttpServletResponse resp, HttpServletRequest req, HttpSession session){

        ResponseData data = new ResponseData();
        Cookie[] cookies = req.getCookies();
        data.setData(0);
        if (cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("username")){
                    cookie.setMaxAge(0);
                    session.invalidate();
                    resp.addCookie(cookie);
                    data.setData(1); // Set data = 0 if delete cookie success
                }
            }
        }
        return data;
    }
}
