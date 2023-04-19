package groupthree.shopflipmart.service;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.repository.QuestionRepo;
import groupthree.shopflipmart.service.Imp.QuestionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService implements QuestionServiceImp {

    @Autowired
    QuestionRepo questionRepo;

    @Override
    public boolean ask(String content, Product product, User user) {
        return questionRepo.ask(content,product,user) >= 1;
    }
}
