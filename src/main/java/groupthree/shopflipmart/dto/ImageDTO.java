package groupthree.shopflipmart.dto;

import groupthree.shopflipmart.entity.Image;
import groupthree.shopflipmart.entity.Product;

public class ImageDTO {

    private int id;
    private String src;
    private int productId;

    public void getImageDTO(Image image){
        Product product = image.getProduct();

        this.id = image.getId();
        this.src = image.getSrc();
        this.productId = product.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
