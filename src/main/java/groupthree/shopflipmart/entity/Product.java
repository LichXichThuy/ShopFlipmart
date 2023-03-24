package groupthree.shopflipmart.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String desc;

    @Column(name = "tag")
    private String tag;

    @Column(name = "price")
    private double price;

    @Column(name = "discount")
    private float discount;

    @Column(name = "vote_everage")
    private float voteEver;

    @Column(name = "amount")
    private int amount;

    @Column(name = "input_date")
    @Temporal(TemporalType.DATE)
    private Date inputDate;

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<Image> image;

    @OneToMany(mappedBy = "product_1")
    private Set<Compare> compares1;

    @OneToMany(mappedBy = "product_2")
    private Set<Compare> compares2;

    @OneToMany(mappedBy = "product_3")
    private Set<Compare> compares3;

    @OneToMany(mappedBy = "product")
    private Set<Rating> rating;

    @OneToMany(mappedBy = "product")
    private Set<Wishlist> wishlist;

    @OneToMany(mappedBy = "product")
    private Set<OrderProduct> orderProduct;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getVoteEver() {
        return voteEver;
    }

    public void setVoteEver(float voteEver) {
        this.voteEver = voteEver;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Image> getImage() {
        return image;
    }

    public void setImage(Set<Image> image) {
        this.image = image;
    }

    public Set<Compare> getCompares1() {
        return compares1;
    }

    public void setCompares1(Set<Compare> compares1) {
        this.compares1 = compares1;
    }

    public Set<Compare> getCompares2() {
        return compares2;
    }

    public void setCompares2(Set<Compare> compares2) {
        this.compares2 = compares2;
    }

    public Set<Compare> getCompares3() {
        return compares3;
    }

    public void setCompares3(Set<Compare> compares3) {
        this.compares3 = compares3;
    }

    public Set<Rating> getRating() {
        return rating;
    }

    public void setRating(Set<Rating> rating) {
        this.rating = rating;
    }

    public Set<Wishlist> getWishlist() {
        return wishlist;
    }

    public void setWishlist(Set<Wishlist> wishlist) {
        this.wishlist = wishlist;
    }

    public Set<OrderProduct> getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(Set<OrderProduct> orderProduct) {
        this.orderProduct = orderProduct;
    }
}
