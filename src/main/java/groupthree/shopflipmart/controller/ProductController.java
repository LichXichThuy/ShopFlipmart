package groupthree.shopflipmart.controller;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.repository.CategoryRepository;
import groupthree.shopflipmart.service.Imp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceImp productServiceImp;

    @Autowired
    CategoryServiceImp categoryServiceImp;

    @Autowired
    RatingServiceImp ratingServiceImp;

    @Autowired
    ImageServiceImp imageServiceImp;

    @Autowired
    QuestionServiceImp questionServiceImp;

    @Autowired
    OrderProductServiceImp orderProductServiceImp;

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("")
    public ModelAndView productDetailPage(@RequestParam int productID, HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        String email = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                email = cookie.getValue();
                break;
            }
        }
        User user = userServiceImp.getUserByEmail(email);

        Product product = productServiceImp.getProductById(productID);

        ModelAndView mav = new ModelAndView("detail");
        mav.addObject("product", product);
        mav.addObject("listcategory", categoryServiceImp.findAll());
        mav.addObject("listImage", imageServiceImp.getAllImageByProduct(product));
        mav.addObject("ratingByProduct", ratingServiceImp.getAllByProduct(product));
        mav.addObject("ratingByProductAndUser", ratingServiceImp.getByProductAndUser(product, user));
        mav.addObject("listTopDiscount", productServiceImp.getTopDiscount());
        mav.addObject("listImageGroupProduct", imageServiceImp.getAllImageGroupByProduct());
        mav.addObject("listAllTag", productServiceImp.getAllTag());
        mav.addObject("listOPByProductAndUser", orderProductServiceImp.findOrderProductByProductAndUser(product, user));
        mav.addObject("productsByTag", productServiceImp.allProductWithTag(product.getTag()));

        return mav;
    }
}
