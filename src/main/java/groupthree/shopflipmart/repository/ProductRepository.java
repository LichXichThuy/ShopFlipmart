package groupthree.shopflipmart.repository;

import groupthree.shopflipmart.entity.Category;
import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategory(Category category);

    List<Product> getByTag(String tag);

    @Query(value = "select distinct p.tag from product p\n" +
            "where p.cate_id = ?1\n" +
            "order by p.tag;", nativeQuery = true)
    List<String> findTagWithCategoryId(Integer cateId);

    @Query(value = "select * from product\n" +
            "where input_date >= date_sub(curdate(), interval ?1 day)", nativeQuery = true)
    List<Product> getNewProducts(int days);

    //Favourite product
    @Query("select p from wishlist w join product p on w.product = p where w.user = ?1")
    List<Product> getLoveProducts(User user);

    @Query(value = "select * from product p order by p.discount desc limit ?1", nativeQuery = true)
    List<Product> findTopDiscount(int top);

    @Query(value = "select *\n" +
            "from product p\n" +
            "order by vote_everage desc\n" +
            "limit 4", nativeQuery = true)
    List<Product> findTop4OrderByVoteEverDesc();

    @Query(value = "select distinct(p.tag)from product p", nativeQuery = true)
    List<String> getAllTag();

}
