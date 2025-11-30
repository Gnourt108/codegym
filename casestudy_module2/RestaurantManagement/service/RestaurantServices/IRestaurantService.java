package casestudy_module2.RestaurantManagement.service.RestaurantServices;

import casestudy_module2.RestaurantManagement.entity.Dish;
import casestudy_module2.RestaurantManagement.entity.Restaurant;
import casestudy_module2.RestaurantManagement.service.IAccountService;

import java.util.HashMap;
import java.util.List;

public interface IRestaurantService {

    boolean register(Restaurant restaurant);
    boolean login(String username, String password);
    boolean changePassword(Integer restaurantId, String oldPassword, String newPassword);
    boolean updateProfile(Restaurant restaurant);

    Restaurant getById(Integer restaurantId);
    Restaurant getByUsername(String username);
    List<Restaurant> getAllRestaurants();
    List<Restaurant> searchByUsername(String username);

    boolean isAccountLocked(Integer restaurantId);
}
