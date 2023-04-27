package groupthree.shopflipmart.controller.api;

import groupthree.shopflipmart.common.Constant;
import groupthree.shopflipmart.dto.CartDTO;
import groupthree.shopflipmart.entity.OrderProduct;
import groupthree.shopflipmart.entity.Orders;
import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.payload.ResponseData;
import groupthree.shopflipmart.service.Imp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class ApiCart {

    @Autowired
    CartServiceImp cartService;

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    OrdersServiceImp ordersServiceImp;

    @Autowired
    OrderProductServiceImp orderProductServiceImp;

    @Autowired
    ProductServiceImp productServiceImp;

    Constant constant = new Constant();

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
            session.setMaxInactiveInterval(constant.TIME_COOKIES_SESSIONS);
        } catch (Exception e){
            System.err.println("Error in ApiCart:  " + e.getMessage());
        }
        return cart;
    }

    @PostMapping("/delete{id}")
    public HashMap<Integer, CartDTO> DeleteCart(HttpServletRequest request, HttpSession session, @PathVariable int id) {
        HashMap<Integer, CartDTO> cart = new HashMap<>();
        try {
            cart = (HashMap<Integer, CartDTO>) session.getAttribute("Cart");
            if (cart == null) {
                cart = new HashMap<Integer, CartDTO>();
            }
            cart = cartService.deleteCart(id, cart);
            session.setAttribute("Cart", cart);
            session.setAttribute("totalQuantyCart", cartService.totalQuantyCart(cart));
            session.setAttribute("TotalPriceCart", cartService.totalPrice(cart));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkOut(HttpServletRequest request, HttpSession session, @RequestParam(required = false) String address){
        ResponseData data = new ResponseData();
        data.setData(false);
        data.setDesc("Checkout fail, maybe not enough amount product, try againt");
        double totalPrice;

        HashMap<Integer, CartDTO> cart = (HashMap<Integer, CartDTO>) session.getAttribute("Cart");

        if (cart != null){
            totalPrice = (double) session.getAttribute("TotalPriceCart");
            Orders orders = new Orders();
            Date date = new Date();
            Cookie[] cookies = request.getCookies();
            String email = null;

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    email = cookie.getValue();
                    break;
                }
            }
            User user = userServiceImp.getUserByEmail(email);

            if (!address.equals("")){
                orders.setAddress(address);
            }else if (user.getAddress() != null){
                orders.setAddress(user.getAddress());
            }else {
                data.setData(false);
                data.setDesc("You must input different address!");
                return new ResponseEntity<>(data, HttpStatus.OK);
            }
            orders.setTotal(totalPrice);
            orders.setDate(date);
            orders.setStatus("not done");
            orders.setUser(user);

            boolean saveOrders = ordersServiceImp.saveOrders(orders);

            if (saveOrders){
                orders = ordersServiceImp.getLastByUser(user);
                for(Map.Entry<Integer, CartDTO> itemCart : cart.entrySet()) {
                    OrderProduct orderProduct = new OrderProduct();
                    Product product = productServiceImp.getProductById(itemCart.getKey());

                    orderProduct.setAmount(itemCart.getValue().getAmount());
                    orderProduct.setPrice(itemCart.getValue().getTotalPrice());
                    orderProduct.setShipPrice(5);   // default ship price = 5$
                    orderProduct.setProduct(product);
                    orderProduct.setOrders(orders);

                    boolean saveOP = orderProductServiceImp.saveOP(orderProduct);

                    if (saveOP){
                        orderProduct = orderProductServiceImp.getOPByProductAndOrders(product, orders);
                        boolean saveProduct;
                        if (product.getAmount() < itemCart.getValue().getAmount()) {
                            saveProduct = false;
                        }else {
                            product.setAmount(product.getAmount() - itemCart.getValue().getAmount());
                            saveProduct = productServiceImp.saveProduct(product);
                        }

                        if (saveProduct){
                            data.setData(saveProduct);
                            data.setDesc("Checkout success, continue to shopping");
                        }else {
                            orderProductServiceImp.deleteOP(orderProduct);
                            data.setData(false);
                        }
                    }else {
                        ordersServiceImp.deleteOrders(orders);
                        data.setData(false);
                    }
                }
            }
        }else data.setDesc("Your cart have not product");

        boolean check = (boolean) data.getData();
        if (check){
            session.invalidate();
        }

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
