package groupthree.shopflipmart.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import groupthree.shopflipmart.common.Constant;
import groupthree.shopflipmart.dto.ListProductDTO;
import groupthree.shopflipmart.dto.ProductDTO;
import groupthree.shopflipmart.entity.Category;
import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.repository.CategoryRepository;
import groupthree.shopflipmart.repository.OrderProductRepository;
import groupthree.shopflipmart.repository.ProductRepository;
import groupthree.shopflipmart.service.Imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductServiceImp {

    Constant constant = new Constant();

    private int redisTimeOut = 2*24*60*60*1000;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ListProductDTO listProductDTO;

    @Override
    public List<List<String>> listTagWithCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        Gson gson = new Gson();

        List<List<String>> list = new ArrayList<>();
        String data = (String) redisTemplate.opsForValue().get("listTagWithCategory");

        if (data == null){  // Nếu chưa có dữ liệu trên redis
            for (Category category:categoryList){
                List<String> stringList = productRepository.findTagWithCategoryId(category.getId());
                list.add(stringList);
            }
            // Đẩy dữ liệu lên redis
            redisTemplate.opsForValue().set("listTagWithCategory", gson.toJson(list));
        }else {
            // Chuyển dữ liệu từ String sang List<List<ProductDTO>>
            list = gson.fromJson(data, new TypeToken<List<List<String>>>(){}.getType());
        }
        // Trả ra danh sách gồm các danh sách sản phẩm theo category,
        // mỗi danh sách sản phẩm không có sản phẩm trùng tag
        return list;
    }

    @Override
    public List<ProductDTO> allProductWithTag(String tag) {
        List<Product> productList = productRepository.getByTag(tag);

        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product:productList){
            ProductDTO productDTO = new ProductDTO();

            productDTO.getProductDTO(product);

            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @Override
    public Boolean saveProduct(ProductDTO productDTO) {
        Category category = new Category();
        category.setId(productDTO.getCateId());

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDesc(productDTO.getDesc());
        product.setTag(productDTO.getTag());
        product.setPrice(productDTO.getPrice());
        product.setDiscount(productDTO.getDiscount());
        product.setVoteEver(productDTO.getVoteEver());
        product.setAmount(productDTO.getAmount());
        product.setInputDate(productDTO.getInputDate());
        product.setCategory(category);

        try {
            productRepository.save(product);
            return true;
        } catch (Exception e){
            System.err.println("Error save product in ProductService + " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<ProductDTO> listProductWithCategory(int cateId) {
        List<Product> list = productRepository.findByCategory(categoryRepository.findById(cateId));
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product:list){
            ProductDTO productDTO = new ProductDTO();
            productDTO.getProductDTO(product);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> listProductBestSeller() {
        List<ProductDTO> list = new ArrayList<>();

        for (Integer productId:orderProductRepository.getListProductIdByBestSeller(constant.PRODUCT_BEST_SELLER_TIME)){
            ProductDTO productDTO = new ProductDTO();
            Product product = productRepository.getById(productId);
            productDTO.getProductDTO(product);
            list.add(productDTO);
        }
        return list;
    }

    @Override
    public List<ProductDTO> getNewProducts() {
        List<Product> productList = productRepository.getNewProducts(constant.NEW_PRODUCT_TIME);

        return listProductDTO.getProductDTOList(productList);
    }

    @Override
    public List<ProductDTO> getLoveProducts(User user) {
        List<Product> productList = productRepository.getLoveProducts(user);

        return  listProductDTO.getProductDTOList(productList);
    }

    @Override
    public List<ProductDTO> getTopDiscount() {
        List<Product> productList = productRepository.findTopDiscount(constant.TOP_DISCOUNT);

        return listProductDTO.getProductDTOList(productList);
    }

    @Override
    public List<Product> getTop4VoteEver() {
        return productRepository.findTop4OrderByVoteEverDesc();
    }

    @Override
    public List<String> getAllTag() {
        return productRepository.getAllTag();
    }

    @Override
    public Product getProductById(int productID) {
        return productRepository.findById(productID).get();
    }
}
