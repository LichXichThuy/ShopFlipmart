package groupthree.shopflipmart.controller.api;

import groupthree.shopflipmart.dto.CartDTO;
import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.service.Imp.CartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/api/cart")
public class ApiCart {

    @Autowired
    CartServiceImp cartService;

    @PostMapping("/add{id}")
    public HashMap<Integer, CartDTO> addToCart(HttpServletRequest request, HttpSession session, @PathVariable int id){

        HashMap<Integer, CartDTO> cart = new HashMap<>();

        try {
            cart = (HashMap<Integer, CartDTO>) session.getAttribute("Cart");
            if (cart == null){
                cart = new HashMap<Integer, CartDTO>();
            }
            if(request.getParameter("quanty")!=null && !request.getParameter("quanty").isEmpty()){
                int quanty = Integer.parseInt(request.getParameter("quanty").toString());
                cart = cartService.addCart(id, cart, quanty);
            }else{
                cart = cartService.addCart(id, cart, 1);
            }
            session.setAttribute("Cart", cart);
            session.setAttribute("totalQuantyCart", cartService.totalQuantyCart(cart));
            session.setAttribute("TotalPriceCart", cartService.totalPrice(cart));
        } catch (Exception e){
            System.err.println("Error in ApiCart:  " + e.getMessage());
        }
        return cart;
    }
}
