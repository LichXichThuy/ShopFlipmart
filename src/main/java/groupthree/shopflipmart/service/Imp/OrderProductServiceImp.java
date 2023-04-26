package groupthree.shopflipmart.service.Imp;

import groupthree.shopflipmart.entity.OrderProduct;
import groupthree.shopflipmart.entity.Orders;
import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;

import java.util.List;

public interface OrderProductServiceImp {
    boolean saveOP(OrderProduct orderProduct);

    void deleteOP(OrderProduct orderProduct);

    OrderProduct getOPByProductAndOrders(Product product, Orders orders);

    List<OrderProduct> findOrderProductByProductAndUser(Product product, User user);
}
