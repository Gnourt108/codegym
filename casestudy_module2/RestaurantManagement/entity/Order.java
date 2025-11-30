package casestudy_module2.RestaurantManagement.entity;

import java.util.List;

public class Order {
    private Integer orderID;
    private List<Dish> dishes;
    private String status;
    private boolean isPay;
    private Integer customerId;
    private Integer shipperId;

    public Order(Integer orderID, List<Dish> dishes, String status, boolean isPay) {
        this.orderID = orderID;
        this.dishes = dishes;
        this.status = status;
        this.isPay = isPay;
    }

    public Order() {
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    public void setCustomerId(int i) {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getShipperId() {
        return shipperId;
    }

    public void setShipperId(Integer shipperId) {
        this.shipperId = shipperId;
    }
}
