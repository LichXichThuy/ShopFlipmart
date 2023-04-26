package groupthree.shopflipmart.service;

import groupthree.shopflipmart.dto.CartDTO;
import groupthree.shopflipmart.dto.ProductDTO;
import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.repository.ProductRepository;
import groupthree.shopflipmart.service.Imp.CartServiceImp;
import groupthree.shopflipmart.service.Imp.ImageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartService implements CartServiceImp {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImageServiceImp imageServiceImp;

    @Override
    public HashMap<Integer, CartDTO> addCart(int id, HashMap<Integer, CartDTO> cart, int quanty) {

        CartDTO itemCart = new CartDTO();
        try {
            Product product = productRepository.findById(id).get();
            ProductDTO productDTO = new ProductDTO();
            productDTO.getProductDTO(product);

            if (product != null && cart.containsKey(id)){
                itemCart = cart.get(id);
                itemCart.setAmount(itemCart.getAmount() + quanty);
                itemCart.setTotalPrice(itemCart.getAmount() * itemCart.getProduct().getPrice() * (100 - itemCart.getProduct().getDiscount()) / 100);
            }else {
                if (quanty > 1){
                    itemCart.setAmount(quanty);
                    itemCart.setProduct(productDTO);
                    itemCart.setTotalPrice(itemCart.getAmount() * itemCart.getProduct().getPrice() * (100 - itemCart.getProduct().getDiscount()) / 100);
                }else {
                    itemCart.setAmount(1);
                    itemCart.setProduct(productDTO);
                    itemCart.setTotalPrice(itemCart.getAmount() * itemCart.getProduct().getPrice() * (100 - itemCart.getProduct().getDiscount()) / 100);
                }
                itemCart.setImage(imageServiceImp.getFirstImageByProduct(product).getSrc());

            }
            if (itemCart.getAmount() > itemCart.getProduct().getAmount()) {
                itemCart.setAmount(itemCart.getProduct().getAmount());
            }
            cart.put(id, itemCart);
        }catch (Exception e){
            System.err.println("Error in CartService: " + e.getMessage());
        }
        return cart;
    }

    @Override
    public HashMap<Integer, CartDTO> deleteCart(int id, HashMap<Integer, CartDTO> cart) {
        try {
            if (cart == null){
                return cart;
            }
            if (cart.containsKey(id)){
                cart.remove(id);
            }
        }catch (Exception e){
            System.err.println("Error in CartService: " + e.getMessage());
        }
        return cart;
    }

    @Override
    public Integer totalQuantyCart(HashMap<Integer, CartDTO> cart) {
        int totalQuanty = 0;
        try {
            for(Map.Entry<Integer, CartDTO> itemCart : cart.entrySet()) {
                totalQuanty += itemCart.getValue().getAmount();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalQuanty;
    }

    @Override
    public Double totalPrice(HashMap<Integer, CartDTO> cart) {
        double totalPrice = 0;
        try {
            for(Map.Entry<Integer, CartDTO> itemCart : cart.entrySet()) {
                totalPrice += itemCart.getValue().getTotalPrice();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPrice;
    }
}
