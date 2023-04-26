package groupthree.shopflipmart.controller;

import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.payload.ResponseData;
import groupthree.shopflipmart.repository.CategoryRepository;
import groupthree.shopflipmart.security.CookieService;
import groupthree.shopflipmart.security.FilterChain;
import groupthree.shopflipmart.service.Imp.CategoryServiceImp;
import groupthree.shopflipmart.service.Imp.ImageServiceImp;
import groupthree.shopflipmart.service.Imp.ProductServiceImp;
import groupthree.shopflipmart.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    ImageServiceImp imageServiceImp;

    @Autowired
    ProductServiceImp productServiceImp;

    @Autowired
    CategoryServiceImp categoryServiceImp;

    @Autowired
    CookieService cookieService;

    @GetMapping("/login")
    public ModelAndView startLogin(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        ModelAndView mav = new ModelAndView();
//        boolean authenticated = false;
//
//        if (cookies != null && cookies.length > 0) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("username")) {
//                    authenticated = true;
//                    break;
//                }
//            }
//        }
//        if (authenticated){

//            List<String> listClass = new ArrayList<>();
//            listClass.add("icon fa fa-shopping-bag");
//            listClass.add("icon fa fa-laptop");
//            listClass.add("icon fa fa-paw");
//            listClass.add("icon fa fa-clock-o");
//            listClass.add("icon fa fa-diamond");
//            listClass.add("icon fa fa-heartbeat");
//            listClass.add("icon fa fa-paper-plane");
//            listClass.add("icon fa fa-futbol-o");
//            listClass.add("icon fa fa-envira");
//
//            mav.addObject("listcategory", categoryServiceImp.findAll());
//            mav.addObject("listTagWithCategory", productServiceImp.listTagWithCategory());
//            mav.addObject("listClass", listClass);
//            mav.addObject("listBestSeller", productServiceImp.listProductBestSeller());
//            mav.addObject("listNewProduct", productServiceImp.getNewProducts());
//            mav.addObject("listImageGroupProduct", imageServiceImp.getAllImageGroupByProduct());
//            mav.addObject("listTopDiscount", productServiceImp.getTopDiscount());
//            mav.addObject("listTopVoteEver", productServiceImp.getTop4VoteEver());
//            mav.addObject("listAllTag", productServiceImp.getAllTag());
//            mav.setViewName("home");
//        } else mav.setViewName("sign-in");
        mav.setViewName("sign-in");
        return mav;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(
            HttpServletResponse resp,
            HttpServletRequest req,
            HttpSession session,
            @RequestParam String email,
            @RequestParam String password){
        ResponseData data = cookieService.cookieLogin(resp,req,session, email,password);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/signout")
    public ModelAndView logout(
            HttpServletResponse resp,
            HttpServletRequest req,
            HttpSession session
    ){
        ModelAndView mav = new ModelAndView("sign-in");
        cookieService.cookieLogout(resp,req, session);

        return mav;
    }

    @GetMapping("/account")
    public ModelAndView account(){
        ModelAndView mav = new ModelAndView("account");

        mav.addObject("listcategory", categoryServiceImp.findAll());
        return mav;
    }
}
