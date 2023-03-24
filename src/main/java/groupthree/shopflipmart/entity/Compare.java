package groupthree.shopflipmart.entity;

import javax.persistence.*;

@Entity(name = "compare")
public class Compare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id_1")
    private Product product_1;

    @ManyToOne
    @JoinColumn(name = "product_id_2")
    private Product product_2;

    @ManyToOne
    @JoinColumn(name = "product_id_3")
    private Product product_3;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct_1() {
        return product_1;
    }

    public void setProduct_1(Product product_1) {
        this.product_1 = product_1;
    }

    public Product getProduct_2() {
        return product_2;
    }

    public void setProduct_2(Product product_2) {
        this.product_2 = product_2;
    }

    public Product getProduct_3() {
        return product_3;
    }

    public void setProduct_3(Product product_3) {
        this.product_3 = product_3;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
