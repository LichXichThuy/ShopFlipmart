package groupthree.shopflipmart.controller.api;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.Rating;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.payload.ResponseData;
import groupthree.shopflipmart.service.Imp.RatingServiceImp;
import groupthree.shopflipmart.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ApiRating {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    RatingServiceImp ratingServiceImp;

    @PostMapping("/api/review/add")
    public ResponseEntity<?> addRating(HttpServletRequest request, @RequestParam int id, @RequestParam int star,
                          @RequestParam String content){

        ResponseData data = new ResponseData();
        Product product = new Product();
        Cookie[] cookies = request.getCookies();
        String email = null;
        Rating rating = new Rating();

        product.setId(id);
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                email = cookie.getValue();
                break;
            }
        }
        User user = userServiceImp.getUserByEmail(email);

        rating.setContent(content);
        rating.setStar(star);
        rating.setProduct(product);
        rating.setUser(user);

        data.setData(ratingServiceImp.saveRating(rating));

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
