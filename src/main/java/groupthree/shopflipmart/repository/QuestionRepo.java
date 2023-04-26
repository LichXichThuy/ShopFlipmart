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
}
