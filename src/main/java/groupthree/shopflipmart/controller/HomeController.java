package groupthree.shopflipmart.controller;

import groupthree.shopflipmart.dto.CartDTO;
import groupthree.shopflipmart.entity.Category;
import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.repository.CategoryRepository;
import groupthree.shopflipmart.service.Imp.CategoryServiceImp;
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
import javax.servlet.http.HttpSession;
import java.util.*;
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
    CategoryServiceImp categoryServiceImp;

    @GetMapping("")
    public ModelAndView home(HttpServletRequest request, HttpSession session) {

        ModelAndView mav = new ModelAndView("home");

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

        mav.addObject("listcategory", categoryServiceImp.findAll());
        mav.addObject("listTagWithCategory", productServiceImp.listTagWithCategory());
        mav.addObject("listClass", listClass);
        mav.addObject("listBestSeller", productServiceImp.listProductBestSeller());
        mav.addObject("listNewProduct", productServiceImp.getNewProducts());
        mav.addObject("listImageGroupProduct", imageServiceImp.getAllImageGroupByProduct());
        mav.addObject("listTopDiscount", productServiceImp.getTopDiscount());
        mav.addObject("listTopVoteEver", productServiceImp.getTop4VoteEver());
        mav.addObject("listAllTag", productServiceImp.getAllTag());

        return mav;
    }
}
