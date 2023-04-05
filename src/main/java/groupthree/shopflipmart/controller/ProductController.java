package groupthree.shopflipmart.controller;

import groupthree.shopflipmart.dto.ProductDTO;
import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.payload.ResponseData;
import groupthree.shopflipmart.service.Imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceImp imp;

    @GetMapping("/newProducts")
    public ResponseEntity<?> getNewProducts(){
        List<ProductDTO> dtoList = imp.getNewProducts();
        ResponseData data = new ResponseData();
        data.setData(dtoList);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/loveProducts")
    public ResponseEntity<?> getLoveProducts(
            @RequestParam int id
    ){
        User user = new User();
        user.setId(id);
        List<ProductDTO> dtoList = imp.getLoveProducts(user);
        ResponseData data = new ResponseData();
        data.setData(dtoList);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/compare")
    public ResponseEntity<?> addToCompare(
            @RequestParam int pro1,
            @RequestParam int pro2,
            @RequestParam int pro3,
            @RequestParam int userId
    ){
        User user = new User();
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();

        product1.setId(pro1);
        product2.setId(pro2);
        product3.setId(pro3);
        user.setId(userId);

        boolean check = imp.addToCompare(product1,product2,product3,user);
        ResponseData data = new ResponseData();
        data.setData(check);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @GetMapping("/showCompare")
    public ResponseEntity<?> showCompare(@RequestParam int id){
        User user = new User();
        user.setId(id);
        List<ProductDTO> dtoList = imp.getLoveProducts(user);
        ResponseData data = new ResponseData();
        data.setData(dtoList);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/getQuestion")
    public ResponseEntity<?> getQuestion(@RequestParam int id){
        User user = new User();
        user.setId(id);
        String question = imp.showQuestion(user);
        ResponseData data = new ResponseData();
        data.setData(question);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
}
