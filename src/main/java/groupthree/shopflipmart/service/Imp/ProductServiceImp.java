package groupthree.shopflipmart.service.Imp;

import groupthree.shopflipmart.dto.ProductDTO;
import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductServiceImp {
    public List<ProductDTO> getNewProducts();
    public List<ProductDTO> getLoveProducts(User userId);

    @Transactional
    public boolean addToCompare(Product product1, Product product2, Product product3, User userId);

    public List<ProductDTO> showCompare(User userId);

    public String showQuestion(User userId);
    @Transactional
    public boolean ask(String content, Product productId, User userId);
}
