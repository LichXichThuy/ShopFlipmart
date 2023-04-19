package groupthree.shopflipmart.service.Imp;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;

public interface WishlistServiceImp {
    boolean addWishList(Product product, User user);
}
