package groupthree.shopflipmart.service;

import groupthree.shopflipmart.entity.Orders;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.repository.OrdersRepo;
import groupthree.shopflipmart.service.Imp.OrdersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService implements OrdersServiceImp {

    @Autowired
    OrdersRepo ordersRepo;

    @Override
    public boolean saveOrders(Orders orders) {
        try {
            ordersRepo.save(orders);
            return true;
        }catch (Exception e){
            System.out.println("Error save Orders: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void deleteOrders(Orders orders) {
        ordersRepo.delete(orders);
    }

    @Override
    public Orders getLastByUser(User user) {
        return ordersRepo.getFirstByUserOrderByIdDesc(user);
    }
}
