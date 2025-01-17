package sg.edu.nus.iss.day_25_workshop_subscriber.model;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class OrderDetails {

    @NotBlank(message="Product Name cannot be blank")
    @NotNull(message="Product Name cannot be null")
    @Size(max=64, message="Product Name must be less than 64 characters")
    private String product;

    @NotNull(message="Price cannot be null")
    @Digits(integer=2, fraction=2, message="Unit price must be in the format xx.xx")
    private Double unitPrice;

    @NotNull(message = "Discount cannot be null")
    @Digits(integer = 2, fraction = 2, message = "Discount must be in the format xx.xx")
    private Double discount = 0.1;

    @NotNull(message="Quantity cannot be null")
    private Integer quantity;

    

    public OrderDetails() {
    }

    public OrderDetails(
            @NotBlank(message = "Product Name cannot be blank") @NotNull(message = "Product Name cannot be null") @Size(max = 64, message = "Product Name must be less than 64 characters") String product,
            @NotNull(message = "Price cannot be null") @Digits(integer = 2, fraction = 2, message = "Unit price must be in the format xx.xx") Double unitPrice,
            @NotNull(message = "Discount cannot be null") @Digits(integer = 2, fraction = 2, message = "Discount must be in the format xx.xx") Double discount,
            @NotNull(message = "Quantity cannot be null") Integer quantity) {
        this.product = product;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    
    
}
