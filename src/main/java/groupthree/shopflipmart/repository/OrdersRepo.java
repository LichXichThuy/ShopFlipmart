package groupthree.shopflipmart.repository;

import groupthree.shopflipmart.entity.Orders;
import groupthree.shopflipmart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Integer> {

    @Override
    void delete(Orders entity);

    Orders getFirstByUserOrderByIdDesc(User user);
}
