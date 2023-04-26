package groupthree.shopflipmart.controller.api;

import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.payload.ResponseData;
import groupthree.shopflipmart.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class ApiUser {

    @Autowired
    UserServiceImp userServiceImp;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestParam String email, @RequestParam String name,
                                    @RequestParam String phone, @RequestParam String password){
        ResponseData data = new ResponseData();
        data.setData(0); // Default signup not success
        if (userServiceImp.getUserByEmail(email) == null){
            if (userServiceImp.saveUser(email, name, Integer.parseInt(phone), password)){
                data.setData(1); // Signup success
            }
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/change/password")
    public ResponseEntity<?> changePassword(HttpServletRequest request,
            @RequestParam String curPassword, @RequestParam String newPassword){
        Cookie[] cookies = request.getCookies();
        boolean checkPass = false;
        ResponseData data = new ResponseData();
        data.setData(false);
        String email = null;
        User user = new User();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                email = cookie.getValue();
                user = userServiceImp.getUserByEmail(email);
                checkPass = user.getPassword().equals(curPassword);
                break;
            }
        }
        if (newPassword.equals(user.getPassword())) {
            data.setDesc("Current password equal new password, please change new password");
        }else if (checkPass){
            data.setDesc("Error change password, please change again");
            data.setData(userServiceImp.changePassword(newPassword, email)); // data = true if change success
        }else {
            data.setDesc("Wrong current pasword");
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/change/infor")
    public ResponseEntity<?> changeInfor(
            HttpServletRequest request,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String addressCom){
        Cookie[] cookies = request.getCookies();
        ResponseData data = new ResponseData();
        data.setData(false);
        String email = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                email = cookie.getValue();
                break;
            }
        }
        User user = userServiceImp.getUserByEmail(email);
        if (!name.equals("")) user.setName(name);
        if (!phone.equals("")) user.setPhoneNum(Integer.parseInt(phone));
        if (!address.equals("")) user.setAddress(address);
        if (!addressCom.equals("")) user.setAddressComment(addressCom);
        data.setData(userServiceImp.changeInfor(user));

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
