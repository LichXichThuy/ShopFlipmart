package groupthree.shopflipmart.service;

import groupthree.shopflipmart.entity.OrderProduct;
import groupthree.shopflipmart.entity.Orders;
import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.repository.OrderProductRepository;
import groupthree.shopflipmart.service.Imp.OrderProductServiceImp;
import groupthree.shopflipmart.service.Imp.OrdersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductService implements OrderProductServiceImp {

    @Autowired
    OrderProductRepository orderProductRepository;

    @Override
    public boolean saveOP(OrderProduct orderProduct) {
        try {
            orderProductRepository.save(orderProduct);
            return true;
        }catch (Exception e){
            System.out.println("Error save OrderProduct: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void deleteOP(OrderProduct orderProduct) {
        orderProductRepository.delete(orderProduct);
    }

    @Override
    public OrderProduct getOPByProductAndOrders(Product product, Orders orders) {
        return orderProductRepository.getByProductAndOrders(product, orders);
    }

    @Override
    public List<OrderProduct> findOrderProductByProductAndUser(Product product, User user) {
        return orderProductRepository.findOrderProductByProductAndUser(product, user);
    }

}
