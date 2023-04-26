package groupthree.shopflipmart.controller.api;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.payload.ResponseData;
import groupthree.shopflipmart.service.Imp.QuestionServiceImp;
import groupthree.shopflipmart.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/question")
public class ApiQuestion {

    @Autowired
    QuestionServiceImp questionServiceImp;

    @Autowired
    UserServiceImp userServiceImp;

    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@RequestParam int productId, @RequestParam String content, HttpServletRequest request){
        ResponseData data = new ResponseData();
        Product product = new Product();
        Cookie[] cookies = request.getCookies();
        String email = null;

        product.setId(productId);
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                email = cookie.getValue();
                break;
            }
        }
        User user = userServiceImp.getUserByEmail(email);

        data.setData(questionServiceImp.saveQuestion(content, product, user));

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
