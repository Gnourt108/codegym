package casestudy_module2.RestaurantManagement.service.DishServices;

import casestudy_module2.RestaurantManagement.entity.Dish;
import casestudy_module2.RestaurantManagement.repository.DishRepository;

import java.util.ArrayList;
import java.util.List;

public class DishService implements IDishService{
    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean addDish(Dish dish) {
        if(dish == null || dish.getNameDish() == null){
            return false;
        }

        if(dish.getPrice() <= 0){
            return false;
        }

        repository.addDish(dish);
        return true;
    }

    @Override
    public boolean deleteDish(String dishName) {
        if (dishName == null || dishName.trim().isEmpty()) {
            return false;
        }

        repository.deleteDish(dishName);
        return true;
    }

    @Override
    public boolean updateDish(Dish dish) {
        if (dish == null || dish.getIdDish() == null) {
            return false;
        }

        if (repository.getDishById(dish.getIdDish()) == null) {
            return false;
        }

        repository.updateDish(dish);
        return true;
    }

    @Override
    public List<Dish> getAllDishes() {
        return repository.getAllDishes();
    }

    @Override
    public Dish getDishById(Integer dishId) {
        if (dishId == null) {
            return null;
        }
        return repository.getDishById(dishId);
    }

    @Override
    public List<Dish> searchDishesByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return repository.searchDishesByName(name);
    }

    @Override
    public List<Dish> getDiscountedDishes() {
        return repository.getDiscountedDishes();
    }

    @Override
    public List<Dish> getBestSellerDishes() {
        return repository.getBestSellerDishes();
    }

    @Override
    public double calculateDiscountedPrice(Dish dish) {
        if (dish == null) {
            return 0;
        }
        return repository.calculateDiscountedPrice(dish);
    }


    @Override
    public List<Dish> sortDishesByPrice(boolean ascending) {
        List<Dish> dishes = new ArrayList<>();
        dishes.sort((d1, d2) -> {
            double price1 = calculateDiscountedPrice(d1);
            double price2 = calculateDiscountedPrice(d2);
            if(ascending){
                return Double.compare(price1, price2);
            }else{
                return Double.compare(price2, price1);
            }
        });
        return dishes;
    }

    @Override
    public boolean setDiscount(Integer dishId, double discountPercent, int daysValid) {
        if(dishId == null || discountPercent < 0 || discountPercent > 1){
            return false;
        }
        Dish dish = repository.getDishById(dishId);
        if(dish == null){
            return false;
        }

        dish.setEndow(true);
        dish.setIsDiscount(discountPercent);
        long deadline = System.currentTimeMillis() + (daysValid * 24L * 60 * 60 * 1000);
        dish.setDiscountDeadline(deadline);
        return updateDish(dish);
    }

    @Override
    public boolean removeDiscount(Integer dishId) {
        if(dishId == null){
            return false;
        }

        Dish dish = repository.getDishById(dishId);

        if(dish == null){
            return false;
        }

        dish.setEndow(false);
        dish.setIsDiscount(0);
        dish.setDiscountDeadline(0);

        return updateDish(dish);
    }

    @Override
    public boolean isDiscountValid(Dish dish) {
        if(dish == null || !dish.isEndow()){
            return false;
        }
        long now = System.currentTimeMillis();
        return dish.getIsDiscount() > 0 && now <= dish.getDiscountDeadline();
    }

    @Override
    public boolean markAsBestSeller(Integer dishId) {
        if (dishId == null) {
            return false;
        }

        Dish dish = repository.getDishById(dishId);
        if (dish == null) {
            return false;
        }

        dish.setBestSeller(true);
        return updateDish(dish);
    }

    @Override
    public boolean unmarkBestSeller(Integer dishId) {
        if (dishId == null) {
            return false;
        }

        Dish dish = repository.getDishById(dishId);
        if (dish == null) {
            return false;
        }

        dish.setBestSeller(false);
        return updateDish(dish);
    }

    @Override
    public boolean isDishAvailable(Integer dishId) {
        return getDishById(dishId) != null;
    }
}
