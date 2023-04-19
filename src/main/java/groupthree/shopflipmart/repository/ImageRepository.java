package groupthree.shopflipmart.repository;

import groupthree.shopflipmart.entity.Image;
import groupthree.shopflipmart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query(value = "select i.*\n" +
            "from image i\n" +
            "join (\n" +
            "\tselect min(id) id, product_id\n" +
            "\tfrom image\n" +
            "\tgroup by product_id\n" +
            ") m on i.id = m.id and i.product_id = m.product_id", nativeQuery = true)
    List<Image> getAllImageGroupByProductId();

    List<Image> findAllByProduct(Product product);

    Image findFirstByProduct(Product product);
}
