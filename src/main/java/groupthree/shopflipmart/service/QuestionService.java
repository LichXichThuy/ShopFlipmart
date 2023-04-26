package groupthree.shopflipmart.service;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.Question;
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
    public boolean saveQuestion(String content, Product product, User user) {
        Question question = new Question();
        question.setContent(content);
        question.setProduct(product);
        question.setUser(user);
        try {
            questionRepo.save(question);
            return true;
        }catch (Exception e){
            System.out.println("Error save Question: " + e.getMessage());
            return false;
        }
    }
}
