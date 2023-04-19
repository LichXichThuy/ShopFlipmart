package groupthree.shopflipmart.controller.api;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.payload.ResponseData;
import groupthree.shopflipmart.service.Imp.WishlistServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishlist")
public class ApiWishlist {

    @Autowired
    WishlistServiceImp wishlistServiceImp;

    @GetMapping("/add")
    public ResponseEntity<?> addWishList(Product product){

        User user = new User();
        user.setId(3);
        ResponseData responseData = new ResponseData();
        responseData.setData(wishlistServiceImp.addWishList(product, user));

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
