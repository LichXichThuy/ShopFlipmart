package groupthree.shopflipmart.service.Imp;

import groupthree.shopflipmart.dto.ProductDTO;
import groupthree.shopflipmart.entity.Category;
import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;

import java.util.List;

public interface ProductServiceImp {
    List<List<String>> listTagWithCategory();

    List<ProductDTO> allProductWithTag(String tag);

    Boolean saveProduct(Product product);

    List<ProductDTO> listProductWithCategory(int cateId);

    List<ProductDTO> listProductBestSeller();

    List<ProductDTO> getNewProducts();

    List<Product> getLoveProducts(User user);

    List<ProductDTO> getTopDiscount();

    List<Product> getTop4VoteEver();

    List<String> getAllTag();

    Product getProductById(int productID);
}
