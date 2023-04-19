package groupthree.shopflipmart.controller;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.repository.CategoryRepository;
import groupthree.shopflipmart.service.Imp.ImageServiceImp;
import groupthree.shopflipmart.service.Imp.ProductServiceImp;
import groupthree.shopflipmart.service.Imp.QuestionServiceImp;
import groupthree.shopflipmart.service.Imp.RatingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceImp productServiceImp;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RatingServiceImp ratingServiceImp;

    @Autowired
    ImageServiceImp imageServiceImp;

    @Autowired
    QuestionServiceImp questionServiceImp;

    @GetMapping("")
    public ModelAndView productDetailPage(@RequestParam int productID) {

        Product product = new Product();
        product.setId(productID);

        User user = new User();
        user.setId(3);

        ModelAndView mav = new ModelAndView("detail");
        mav.addObject("product", productServiceImp.getProductById(productID));
        mav.addObject("listcategory", categoryRepository.findAll());
        mav.addObject("listImage", imageServiceImp.getAllImageByProduct(product));
        mav.addObject("ratingByProduct", ratingServiceImp.getAllByProduct(product));
        mav.addObject("ratingByProductAndUser", ratingServiceImp.getByProductAndUser(product, user));

        return mav;
    }
}
