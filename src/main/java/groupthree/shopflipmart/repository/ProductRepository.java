package groupthree.shopflipmart.repository;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    @Query("select p from product p where current_date() - input_date <= 7")
    public List<Product> getNewProducts();

    //Favourite product
    @Query("select p from wishlist w join product p on w.product = p.id where w.user = ?1")
    public List<Product> getLoveProducts(User userId);

    //Compare
    @Modifying
    @Query("update compare c set c.product_1 = ?1, c.product_2 = ?2, c.product_3 = ?3 where c.user = ?4")
    public int addToCompare(Product product1, Product product2, Product product3, User userId);

    @Query("select p from product p join compare c on " +
            "p.id = c.product_1 or " +
            "p.id = c.product_2 or " +
            "p.id = c.product_3 " +
            "where c.user = ?1")
    public List<Product> showCompare(User userId);

    //Tag: Private question about product
    //Show Question
    @Query("select p.tag from wishlist w join product p on w.product = p.id where w.user = ?1")
    public String getQuestion(User userId);
}
