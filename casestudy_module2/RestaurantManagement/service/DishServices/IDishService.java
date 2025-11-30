package casestudy_module2.RestaurantManagement.service.DishServices;

import casestudy_module2.RestaurantManagement.entity.Dish;

import java.util.List;

public interface IDishService {
    boolean addDish(Dish dish);
    boolean deleteDish(String dishName);
    boolean updateDish(Dish dish);
    List<Dish> getAllDishes();
    Dish getDishById(Integer dishId);
    List<Dish> searchDishesByName(String name);
    List<Dish> getDiscountedDishes();
    List<Dish> getBestSellerDishes();
    double calculateDiscountedPrice(Dish dish);
    List<Dish> sortDishesByPrice(boolean ascending);
    boolean setDiscount(Integer dishId, double discountPercent, int daysValid);
    boolean removeDiscount(Integer dishId);
    boolean isDiscountValid(Dish dish);
    boolean markAsBestSeller(Integer dishId);
    boolean unmarkBestSeller(Integer dishId);
    boolean isDishAvailable(Integer dishId);
}
