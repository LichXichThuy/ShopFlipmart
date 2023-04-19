package groupthree.shopflipmart.dto;

public class CartDTO {

    private int amount;
    private double totalPrice;
    private ProductDTO product;
    private String image;

    public CartDTO() {
    }

    public CartDTO(int amount, double totalPrice, ProductDTO product) {
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.product = product;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
