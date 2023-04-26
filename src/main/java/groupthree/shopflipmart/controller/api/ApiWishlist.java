package groupthree.shopflipmart.controller.api;

import groupthree.shopflipmart.dto.ProductDTO;
import groupthree.shopflipmart.dto.ProductImageDto;
import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.payload.ResponseData;
import groupthree.shopflipmart.service.ImageService;
import groupthree.shopflipmart.service.Imp.ImageServiceImp;
import groupthree.shopflipmart.service.Imp.ProductServiceImp;
import groupthree.shopflipmart.service.Imp.UserServiceImp;
import groupthree.shopflipmart.service.Imp.WishlistServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class ApiWishlist {

    @Autowired
    WishlistServiceImp wishlistServiceImp;

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    ProductServiceImp productServiceImp;

    @Autowired
    ImageServiceImp imageServiceImp;

    @PostMapping("/delete")
    public List<ProductImageDto> delete(@RequestParam int productId, HttpServletRequest request){

        List<Product> list = new ArrayList<>();
        List<ProductImageDto> productImageDtoList = new ArrayList<>();
        try {
            Cookie[] cookies = request.getCookies();
            String email = null;

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    email = cookie.getValue();
                    break;
                }
            }
            User user = userServiceImp.getUserByEmail(email);
            Product product = productServiceImp.getProductById(productId);
            wishlistServiceImp.deleteByProductAndUser(product, user);
//            list = productServiceImp.getLoveProducts(user);

//            for (Product item:list){
//                ProductImageDto productImageDto = new ProductImageDto();
//                productImageDto.getProductImage(item);
//                productImageDto.setImage(imageServiceImp.getFirstImageByProduct(item).getSrc());
//                productImageDtoList.add(productImageDto);
//            }
        }catch (Exception e){
            System.err.println("Error in api wishlist: " + e.getMessage());
        }
        return productImageDtoList;
    }
}
