package casestudy_module2.RestaurantManagement.entity;

import java.util.HashMap;

public class Restaurant extends Account{
    private String hostRestaurant;
    private HashMap<String, Dish> dishes;

    public Restaurant(Integer idUser, String userName, String password, String phoneNumber, String address, boolean isLocked) {
        super(idUser, userName, password, phoneNumber, address, isLocked);
    }

    public String getHostRestaurant() {
        return hostRestaurant;
    }

    public void setHostRestaurant(String hostRestaurant) {
        this.hostRestaurant = hostRestaurant;
    }

    public HashMap<String, Dish> getDishes() {
        return dishes;
    }

    public void setDishes(HashMap<String, Dish> dishes) {
        this.dishes = dishes;
    }
}
