package groupthree.shopflipmart.service.Imp;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.Rating;
import groupthree.shopflipmart.entity.User;

import java.util.List;

public interface RatingServiceImp {

    int countRatingByProduct(Product product);

    List<Rating> getAllByProduct(Product product);

    List<Rating> getByProductAndUser(Product product, User user);

    boolean saveRating(Rating rating);
}
