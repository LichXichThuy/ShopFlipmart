package groupthree.shopflipmart.dto;

import groupthree.shopflipmart.entity.Category;
import groupthree.shopflipmart.entity.Product;

import java.util.Date;

public class ProductImageDto {
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

    private String image;

    public void getProductImage(Product product){
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
