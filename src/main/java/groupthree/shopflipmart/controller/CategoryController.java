package groupthree.shopflipmart.controller;

import com.google.gson.Gson;
import groupthree.shopflipmart.dto.ProductDTO;
import groupthree.shopflipmart.entity.Category;
import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.payload.ResponseData;
import groupthree.shopflipmart.repository.CategoryRepository;
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
    CategoryRepository categoryRepository;

    @Autowired
    ImageServiceImp imageServiceImp;

    @GetMapping("")
    public ModelAndView category(@RequestParam(defaultValue = "1", required = false) int cateId,
                                 @RequestParam(required = false) String tag
                           ){
        List<ProductDTO> dtoList = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAll();
        if (tag == null){
            dtoList = productServiceImp.listProductWithCategory(cateId);
        }else {
            dtoList = productServiceImp.allProductWithTag(tag);
        }

        ModelAndView modelAndView = new ModelAndView("category");
        modelAndView.addObject("listcategory", categoryList);
        modelAndView.addObject("listProduct", dtoList);
        modelAndView.addObject("listImageGroupProduct", imageServiceImp.getAllImageGroupByProduct());

        return modelAndView;
    }
}
