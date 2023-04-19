package groupthree.shopflipmart.service;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.Rating;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.repository.RatingRepository;
import groupthree.shopflipmart.service.Imp.RatingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService implements RatingServiceImp {

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public int countRatingByProduct(Product product) {

        return ratingRepository.countRatingByProduct(product);
    }

    @Override
    public List<Rating> getAllByProduct(Product product) {
        return ratingRepository.findAllByProduct(product);
    }

    @Override
    public List<Rating> getByProductAndUser(Product product, User user) {
        return ratingRepository.findRatingByProductAndUser(product, user);
    }
}
