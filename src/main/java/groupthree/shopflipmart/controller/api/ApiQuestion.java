package groupthree.shopflipmart.controller.api;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.payload.ResponseData;
import groupthree.shopflipmart.service.Imp.QuestionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/question")
public class ApiQuestion {

    @Autowired
    QuestionServiceImp questionServiceImp;

    @GetMapping("/add")
    public ResponseEntity<?> addQuestion(String content, Product product){
        ResponseData responseData = new ResponseData();
        User user = new User();
        user.setId(3);
        responseData.setData(questionServiceImp.ask(content, product, user));

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
