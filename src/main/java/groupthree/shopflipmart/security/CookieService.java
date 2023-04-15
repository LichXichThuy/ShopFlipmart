package groupthree.shopflipmart.security;

import groupthree.shopflipmart.payload.ResponseData;
import groupthree.shopflipmart.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CookieService {

    @Autowired
    UserServiceImp userServiceImp;

    public ResponseData cookieLogin(HttpServletResponse resp, HttpServletRequest req, String email, String password){
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
                data.setDesc("Already log in!");
            }
            else{
                boolean check = userServiceImp.login(email, password);
                if (check) {
                    System.out.println("not login");
                    Cookie cookie = new Cookie("username",email);
                    cookie.setMaxAge(30);
                    resp.addCookie(cookie);
                    data.setDesc(data.getDesc()+" user had to log in and have logged in successfully");
                }
                else {
                    System.out.println("Incorrect email or password1");
                    data.setDesc(data.getDesc()+" user had to log in and have not logged in successfully");
                }
            }
        }
        else {
            boolean check = userServiceImp.login(email, password);
            if (check) {
                data.setDesc(data.getDesc()+" cookie expired and user have logged in successfully!");
                Cookie cookie = new Cookie("username",email);
                cookie.setMaxAge(30);
                resp.addCookie(cookie);
            }
            else {
                data.setDesc(data.getDesc()+" cookie expired and user have not logged in successfully!");
            }
        }
        return data;
    }

    public ResponseData cookieLogout(HttpServletResponse resp, HttpServletRequest req, String email){
        ResponseData data = new ResponseData();
        String result="";
        Cookie[] cookies = req.getCookies();
        Cookie checkCookie = null;
        if (cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getValue().equals(email)){
                    cookie = new Cookie("username",email);
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                    checkCookie = cookie;
                }
            }
        }
        if (checkCookie.getMaxAge() == 0) result = "Cookie deleted!";
        else result = "Failed to delete cookie!";
        data.setDesc(result);
        return data;
    }
}
