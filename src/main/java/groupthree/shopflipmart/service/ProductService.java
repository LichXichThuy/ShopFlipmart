package groupthree.shopflipmart.service;

import groupthree.shopflipmart.dto.ProductDTO;
import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.repository.ProductRepository;
import groupthree.shopflipmart.service.Imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServiceImp {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductDTO> getNewProducts() {
        List<Product> list = productRepository.getNewProducts();
        List<ProductDTO> dtoList = new ArrayList<>();
        for (Product product : list){
            ProductDTO dto = new ProductDTO();
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());
            dto.setDescription(product.getDesc());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public List<ProductDTO> getLoveProducts(User userId) {
        List<Product> list = productRepository.getLoveProducts(userId);
        List<ProductDTO> dtoList = new ArrayList<>();
        for (Product product : list){
            System.out.println("Product: "+product.getName());
            ProductDTO dto = new ProductDTO();
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());
            dto.setDescription(product.getDesc());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public boolean addToCompare(Product product1, Product product2, Product product3, User userId) {
        return productRepository.addToCompare(product1,product2,product3,userId) >= 1;
    }

    @Override
    public List<ProductDTO> showCompare(User userId) {
        List<Product> list = productRepository.showCompare(userId);
        List<ProductDTO> dtoList = new ArrayList<>();
        for (Product product : list){
            ProductDTO dto = new ProductDTO();
            dto.setName(product.getName());
            dto.setDescription(product.getDesc());
            dto.setPrice(product.getPrice());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public String showQuestion(User userId) {
        String question = productRepository.getQuestion(userId);
        return question;
    }

    @Override
    public boolean ask(String content, Product productId, User userId) {
        return productRepository.ask(content,productId,userId) >= 1;
    }
}
