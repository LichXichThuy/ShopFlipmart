package groupthree.shopflipmart.controller.api;

import com.google.gson.Gson;
import groupthree.shopflipmart.dto.ProductDTO;
import groupthree.shopflipmart.payload.ResponseData;
import groupthree.shopflipmart.service.Imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ApiProduct {

    @Autowired
    ProductServiceImp productServiceImp;

    @GetMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO){
        Gson gson = new Gson();
        String data = gson.toJson(productServiceImp.saveProduct(productDTO));

        ResponseData responseData = new ResponseData();
        responseData.setData(data);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
