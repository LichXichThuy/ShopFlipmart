package groupthree.shopflipmart.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phonenumber")
    private int phoneNum;

    @Column(name = "address")
    private String address;

    @Column(name = "address_comment")
    private String addressComment;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<Compare> compares;

    @OneToMany(mappedBy = "user")
    private Set<Rating> rating;

    @OneToMany(mappedBy = "user")
    private Set<Wishlist> wishlist;

    @OneToMany(mappedBy = "user")
    private Set<Orders> orders;

    @OneToMany(mappedBy = "user")
    private Set<Question> questions;

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressComment() {
        return addressComment;
    }

    public void setAddressComment(String addressComment) {
        this.addressComment = addressComment;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Compare> getCompares() {
        return compares;
    }

    public void setCompares(Set<Compare> compares) {
        this.compares = compares;
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

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }
}
