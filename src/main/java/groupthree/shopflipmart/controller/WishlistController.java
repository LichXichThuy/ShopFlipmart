package groupthree.shopflipmart.controller;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.repository.CategoryRepository;
import groupthree.shopflipmart.service.Imp.ImageServiceImp;
import groupthree.shopflipmart.service.Imp.ProductServiceImp;
import groupthree.shopflipmart.service.Imp.WishlistServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mywishlist")
public class WishlistController {

    @Autowired
    WishlistServiceImp wishlistServiceImp;

    @Autowired
    ProductServiceImp productServiceImp;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ImageServiceImp imageServiceImp;

    @GetMapping("")
    public ModelAndView wishList(@RequestParam(required = false, name = "productId") String productId){

        ModelAndView mav = new ModelAndView("my-wishlist");
        User user = new User();
        user.setId(3);

        if (productId != null){
            Product product = productServiceImp.getProductById(Integer.parseInt(productId));

            wishlistServiceImp.addWishList(product, user);
        }
        mav.addObject("listcategory", categoryRepository.findAll());
        mav.addObject("listProduct", productServiceImp.getLoveProducts(user));
        mav.addObject("listImageGroupProduct", imageServiceImp.getAllImageGroupByProduct());


        return mav;
    }
}
