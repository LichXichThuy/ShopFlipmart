package groupthree.shopflipmart.service.Imp;

import groupthree.shopflipmart.dto.CartDTO;

import java.util.HashMap;

public interface CartServiceImp {
    HashMap<Integer, CartDTO> addCart(int id, HashMap<Integer, CartDTO> cart, int quanty);

    HashMap<Integer, CartDTO> deleteCart(int id, HashMap<Integer, CartDTO> cart);

    Integer totalQuantyCart(HashMap<Integer, CartDTO> cart);

    Double totalPrice(HashMap<Integer, CartDTO> cart);
}
