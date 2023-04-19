package groupthree.shopflipmart.dto;

import groupthree.shopflipmart.entity.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class ProductDTO implements Serializable {
    private int id;

    private String name;

    private String desc;

    private String tag;

    private double price;

    private float discount;

    private float voteEver;

    private int amount;

    private Date inputDate;

    private int cateId;

    public void getProductDTO(Product product){
        Category category = new Category();
        category = product.getCategory();

        this.setId(product.getId());
        this.setAmount(product.getAmount());
        this.setDesc(product.getDesc());
        this.setTag(product.getTag());
        this.setDiscount(product.getDiscount());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
        this.setInputDate(product.getInputDate());
        this.setVoteEver(product.getVoteEver());
        this.setCateId(category.getId());
    };

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", tag='" + tag + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", voteEver=" + voteEver +
                ", amount=" + amount +
                ", inputDate=" + inputDate +
                ", cateId=" + cateId +
                '}';
    }

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

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }
}
