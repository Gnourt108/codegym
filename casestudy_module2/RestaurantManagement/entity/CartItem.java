package casestudy_module2.RestaurantManagement.entity;

public class CartItem {
    private Integer customerId;
    private Integer dishId;
    private String dishName;
    private Double dishPrice;
    private Integer quantity;
    private Double subtotal;


    public CartItem() {
    }

    public CartItem(Integer customerId, Integer dishId, Integer quantity) {
        this.customerId = customerId;
        this.dishId = dishId;
        this.quantity = quantity;
    }

    public CartItem(Integer customerId, Integer dishId, String dishName,
                    Double dishPrice, Integer quantity, Double subtotal) {
        this.customerId = customerId;
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    // Getters and Setters
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(Double dishPrice) {
        this.dishPrice = dishPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "customerId=" + customerId +
                ", dishId=" + dishId +
                ", dishName='" + dishName + '\'' +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }
}
