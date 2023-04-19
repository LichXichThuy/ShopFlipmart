package groupthree.shopflipmart.service.Imp;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface QuestionServiceImp {

    @Transactional
    public boolean ask(String content, Product product, User user);
}
