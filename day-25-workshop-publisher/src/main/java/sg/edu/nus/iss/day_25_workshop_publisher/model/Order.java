package sg.edu.nus.iss.day_25_workshop_publisher.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Order {

    @FutureOrPresent(message = "Please select today or a future date")
    @NotNull(message = "Please select order date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    private String customerName;

    @NotBlank(message = "Shipping address cannot be blank")
    @NotNull(message = "Shipping address cannot be null")
    @Size(max = 256, message = "Shipping address must not exceed 256 characters")
    private String shipAddress;
    
    @Size(max = 1000, message = "Notes must not exceed 1000 characters")
    private String notes;

    @NotNull(message = "Tax cannot be null")
    @Digits(fraction = 2, integer = 2, message = "Tax must be in the format xx.xx")
    private Double tax;

    @Valid
    private List<OrderDetails> lineItems;

    

    public Order() {
        this.lineItems = new ArrayList<>();
    }



    public Order(
            @FutureOrPresent(message = "Please select today or a future date") @NotNull(message = "Please select order date") LocalDate orderDate,
            String customerName,
            @NotBlank(message = "Shipping address cannot be blank") @NotNull(message = "Shipping address cannot be null") @Size(max = 256, message = "Shipping address must not exceed 256 characters") String shipAddress,
            @Size(max = 1000, message = "Notes must not exceed 1000 characters") String notes,
            @NotNull(message = "Tax cannot be null") @Digits(fraction = 2, integer = 2, message = "Tax must be in the format xx.xx") Double tax,
            @Valid List<OrderDetails> lineItems) {
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.shipAddress = shipAddress;
        this.notes = notes;
        this.tax = tax;
        this.lineItems = lineItems;
    }



    public LocalDate getOrderDate() {
        return orderDate;
    }



    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }



    public String getCustomerName() {
        return customerName;
    }



    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }



    public String getShipAddress() {
        return shipAddress;
    }



    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }



    public String getNotes() {
        return notes;
    }



    public void setNotes(String notes) {
        this.notes = notes;
    }



    public Double getTax() {
        return tax;
    }



    public void setTax(Double tax) {
        this.tax = tax;
    }



    public List<OrderDetails> getLineItems() {
        return lineItems;
    }



    public void setLineItems(List<OrderDetails> lineItems) {
        this.lineItems = lineItems;
    }



    @Override
    public String toString() {
        return "Order [orderDate=" + orderDate + ", customerName=" + customerName + ", shipAddress=" + shipAddress
                + ", notes=" + notes + ", tax=" + tax + ", lineItems=" + lineItems + "]";
    }

    

    

   
    
}
