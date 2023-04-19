package groupthree.shopflipmart.repository;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.Question;
import groupthree.shopflipmart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    @Modifying
    @Query("update question q set q.content = ?1 where q.product = ?2 and q.user = ?3")
    public int ask(String content, Product product, User user);
}
