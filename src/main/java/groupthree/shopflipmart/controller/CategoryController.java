package groupthree.shopflipmart.controller;

import com.google.gson.Gson;
import groupthree.shopflipmart.dto.ProductDTO;
import groupthree.shopflipmart.entity.Category;
import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.payload.ResponseData;
import groupthree.shopflipmart.repository.CategoryRepository;
import groupthree.shopflipmart.service.Imp.CategoryServiceImp;
import groupthree.shopflipmart.service.Imp.ImageServiceImp;
import groupthree.shopflipmart.service.Imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    ProductServiceImp productServiceImp;

    @Autowired
    CategoryServiceImp categoryServiceImp;

    @Autowired
    ImageServiceImp imageServiceImp;

    @GetMapping("")
    public ModelAndView category(@RequestParam(defaultValue = "1", required = false) int cateId,
                                 @RequestParam(required = false) String tag
                           ){
        List<ProductDTO> dtoList = new ArrayList<>();
        List<Category> categoryList = categoryServiceImp.findAll();
        if (tag == null){
            dtoList = productServiceImp.listProductWithCategory(cateId);
        }else {
            dtoList = productServiceImp.allProductWithTag(tag);
        }

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

        ModelAndView mav = new ModelAndView("category");
        mav.addObject("listClass", listClass);
        mav.addObject("listcategory", categoryList);
        mav.addObject("listTagWithCategory", productServiceImp.listTagWithCategory());
        mav.addObject("listProduct", dtoList);
        mav.addObject("listImageGroupProduct", imageServiceImp.getAllImageGroupByProduct());
        mav.addObject("listAllTag", productServiceImp.getAllTag());

        return mav;
    }
}
