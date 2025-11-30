package casestudy_module2.RestaurantManagement.service.AdminServices;

import casestudy_module2.RestaurantManagement.entity.Customer;
import casestudy_module2.RestaurantManagement.entity.Restaurant;
import casestudy_module2.RestaurantManagement.entity.Shipper;

import java.util.List;

public interface IAdminService {
    boolean lockAccount(Integer userId, String accountType);
    boolean unlockAccount(Integer userId, String accountType);
    List<Customer> getAllCustomers();
    List<Restaurant> getAllRestaurants();
    List<Shipper> getAllShippers();
    boolean deleteAccount(Integer userId, String accountType);
    void viewSystem();
}
