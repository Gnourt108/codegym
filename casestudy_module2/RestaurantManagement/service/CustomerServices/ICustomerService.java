package casestudy_module2.RestaurantManagement.service.CustomerServices;

import casestudy_module2.RestaurantManagement.entity.Customer;
import casestudy_module2.RestaurantManagement.service.IAccountService;

import java.util.List;

public interface ICustomerService{

    boolean register(Customer customer);
    boolean login(String username, String password);
    boolean changePassword(Integer customerId, String oldPassword, String newPassword);
    boolean updateProfile(Customer customer);

    Customer getById(Integer customerId);
    Customer getByUsername(String username);
    List<Customer> getAllCustomers();
    List<Customer> searchByUsername(String username);

    boolean deposit(Integer customerId, double amount);
    boolean withdraw(Integer customerId, double amount);
    double getBalance(Integer customerId);

    boolean isAccountLocked(Integer customerId);
    int getLoginAttempts(String username);
}
