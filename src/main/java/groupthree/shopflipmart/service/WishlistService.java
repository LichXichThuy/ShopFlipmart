package groupthree.shopflipmart.service;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.entity.Wishlist;
import groupthree.shopflipmart.repository.WishlistRepo;
import groupthree.shopflipmart.service.Imp.WishlistServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService implements WishlistServiceImp {

    @Autowired
    WishlistRepo wishlistRepo;

    @Override
    public boolean addWishList(Product product, User user) {
        Wishlist wishlist = new Wishlist();
        wishlist.setProduct(product);
        wishlist.setUser(user);

        if (wishlistRepo.findWishlistsByProductAndUser(product, user).size() > 0) return false;
        else {
            try {
                wishlistRepo.save(wishlist);
                return true;
            } catch (Exception e){
                return false;
            }
        }
    }

    @Override
    public boolean deleteByProductAndUser(Product product, User user) {
        try {
            wishlistRepo.deleteByProductAndUser(product, user);
            return true;
        }catch (Exception e){
            System.out.println("Error delete wishlist: " + e.getMessage());
            return false;
        }
    }
}
