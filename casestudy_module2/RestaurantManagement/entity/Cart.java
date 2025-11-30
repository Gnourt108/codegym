package casestudy_module2.RestaurantManagement.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cart {
    private Integer customerId;
    private List<CartItem> items;
    private Double totalPrice;
    private Integer totalItems;
    private Integer totalQuantity;

    // Constructors
    public Cart() {
        this.items = new ArrayList<>();
    }

    public Cart(Integer customerId) {
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    public Cart(Integer customerId, List<CartItem> items, Double totalPrice,
                Integer totalItems, Integer totalQuantity) {
        this.customerId = customerId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.totalItems = totalItems;
        this.totalQuantity = totalQuantity;
    }

    // Getters and Setters
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "customerId=" + customerId +
                ", totalItems=" + totalItems +
                ", totalQuantity=" + totalQuantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
