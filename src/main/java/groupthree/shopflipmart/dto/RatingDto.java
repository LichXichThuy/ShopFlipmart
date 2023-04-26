package groupthree.shopflipmart.dto;

import groupthree.shopflipmart.entity.Product;
import groupthree.shopflipmart.entity.Rating;
import groupthree.shopflipmart.entity.User;

import javax.persistence.*;

public class RatingDto {
    private int id;
    private String content;
    private int star;
    private int productId;
    private int userId;

    public void getDto(Rating rating){
        this.id = rating.getId();
        this.content = rating.getContent();
        this.star = rating.getStar();
        this.productId = rating.getProduct().getId();
        this.userId = rating.getUser().getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
