package groupthree.shopflipmart.repository;

import groupthree.shopflipmart.common.Constant;
import groupthree.shopflipmart.entity.OrderProduct;
import groupthree.shopflipmart.entity.Orders;
import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {

    @Query(value = "select op.product_id\n" +
            "from orders_product as op\n" +
            "join orders as o on op.orders_id=o.id\n" +
            "where date(o.date_order) >= date_sub(curdate(), interval ?1 day)" +
            "group by op.product_id\n" +
            "order by sum(op.amount) desc\n" +
            "limit 8;", nativeQuery = true)
    List<Integer> getListProductIdByBestSeller(int days);

    @Override
    void delete(OrderProduct entity);

    OrderProduct getByProductAndOrders(Product product, Orders orders);

    @Query(value = "select op from orders_product op join orders o on op.orders = o where op.product = ?1 and o.user = ?2")
    List<OrderProduct> findOrderProductByProductAndUser(Product product, User user);
}
