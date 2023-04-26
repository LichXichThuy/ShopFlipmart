package groupthree.shopflipmart.service.Imp;

import groupthree.shopflipmart.entity.Orders;
import groupthree.shopflipmart.entity.User;

public interface OrdersServiceImp {
    boolean saveOrders(Orders orders);

    void deleteOrders(Orders orders);

    Orders getLastByUser(User user);
}
