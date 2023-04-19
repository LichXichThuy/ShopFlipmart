package groupthree.shopflipmart.repository;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {

    List<Wishlist> findWishlistsByProductAndUser(Product product, User user);

}
