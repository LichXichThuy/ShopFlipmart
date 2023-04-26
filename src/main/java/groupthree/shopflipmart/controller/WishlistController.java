package groupthree.shopflipmart.controller;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.repository.CategoryRepository;
import groupthree.shopflipmart.service.Imp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/mywishlist")
public class WishlistController {

    @Autowired
    WishlistServiceImp wishlistServiceImp;

    @Autowired
    ProductServiceImp productServiceImp;

    @Autowired
    CategoryServiceImp categoryServiceImp;

    @Autowired
    ImageServiceImp imageServiceImp;

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("")
    public ModelAndView wishList(@RequestParam(required = false, name = "productId") String productId, HttpServletRequest request){

        ModelAndView mav = new ModelAndView("my-wishlist");
        Cookie[] cookies = request.getCookies();
        String email = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                email = cookie.getValue();
                break;
            }
        }
        User user = userServiceImp.getUserByEmail(email);

        if (productId != null){
            Product product = new Product();
            product.setId(Integer.parseInt(productId));

            wishlistServiceImp.addWishList(product, user);
        }
        mav.addObject("listcategory", categoryServiceImp.findAll());
        mav.addObject("listProduct", productServiceImp.getLoveProducts(user));
        mav.addObject("listImageGroupProduct", imageServiceImp.getAllImageGroupByProduct());

        return mav;
    }
}
