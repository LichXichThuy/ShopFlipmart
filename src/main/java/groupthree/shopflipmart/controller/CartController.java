package groupthree.shopflipmart.controller;

import groupthree.shopflipmart.service.Imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CartController {

    @Autowired
    CategoryServiceImp categoryServiceImp;

    @GetMapping("/cart")
    public ModelAndView showCart(){

        ModelAndView mav = new ModelAndView("shopping-cart");
        mav.addObject("listcategory", categoryServiceImp.findAll());
        return mav;
    }

    @GetMapping("/checkout")
    public ModelAndView checkout(){
        ModelAndView mav = new ModelAndView("checkout");
        mav.addObject("listcategory", categoryServiceImp.findAll());
        return mav;
    }
}
