package groupthree.shopflipmart.controller;

import groupthree.shopflipmart.entity.Category;
import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.repository.CategoryRepository;
import groupthree.shopflipmart.service.Imp.ImageServiceImp;
import groupthree.shopflipmart.service.Imp.ProductServiceImp;
import groupthree.shopflipmart.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Controller
public class HomeController {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    ImageServiceImp imageServiceImp;

    @Autowired
    ProductServiceImp productServiceImp;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("")
    public ModelAndView home(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("home");
//        Cookie[] cookies = request.getCookies();
//        String email = "";
//
//        if (cookies != null && cookies.length > 0) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("username")) {
//                    email += cookie.getValue();
//                    break;
//                }
//            }
//        }

        List<String> listClass = new ArrayList<>();
        listClass.add("icon fa fa-shopping-bag");
        listClass.add("icon fa fa-laptop");
        listClass.add("icon fa fa-paw");
        listClass.add("icon fa fa-clock-o");
        listClass.add("icon fa fa-diamond");
        listClass.add("icon fa fa-heartbeat");
        listClass.add("icon fa fa-paper-plane");
        listClass.add("icon fa fa-futbol-o");
        listClass.add("icon fa fa-envira");

//        modelAndView.addObject("user", userServiceImp.getUserByEmail(email));
        modelAndView.addObject("listcategory", categoryRepository.findAll());
        modelAndView.addObject("listTagWithCategory", productServiceImp.listTagWithCategory());
        modelAndView.addObject("listClass", listClass);
        modelAndView.addObject("listBestSeller", productServiceImp.listProductBestSeller());
        modelAndView.addObject("listNewProduct", productServiceImp.getNewProducts());
        modelAndView.addObject("listImageGroupProduct", imageServiceImp.getAllImageGroupByProduct());
        modelAndView.addObject("listTopDiscount", productServiceImp.getTopDiscount());
        modelAndView.addObject("listTopVoteEver", productServiceImp.getTop4VoteEver());
        modelAndView.addObject("listAllTag", productServiceImp.getAllTag());

        return modelAndView;
    }
}
