package groupthree.shopflipmart.service.Imp;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface QuestionServiceImp {

    boolean saveQuestion(String content, Product product, User user);
}
