package groupthree.shopflipmart.repository;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.Rating;
import groupthree.shopflipmart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    int countRatingByProduct(Product product);

    List<Rating> findAllByProduct(Product product);

    List<Rating> findRatingByProductAndUser(Product product, User user);
}
