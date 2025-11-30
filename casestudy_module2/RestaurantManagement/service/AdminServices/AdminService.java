package casestudy_module2.RestaurantManagement.service.AdminServices;

import casestudy_module2.RestaurantManagement.entity.Customer;
import casestudy_module2.RestaurantManagement.entity.Restaurant;
import casestudy_module2.RestaurantManagement.entity.Shipper;
import casestudy_module2.RestaurantManagement.repository.CustomerRepository;
import casestudy_module2.RestaurantManagement.repository.RestaurantRepository;
import casestudy_module2.RestaurantManagement.repository.ShipperRepository;

import java.util.List;

public class AdminService implements IAdminService{

    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final ShipperRepository shipperRepository;

    public AdminService(CustomerRepository customerRepository, RestaurantRepository restaurantRepository, ShipperRepository shipperRepository) {
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.shipperRepository = shipperRepository;
    }

    @Override
    public boolean lockAccount(Integer userId, String accountType) {
        switch (accountType.toLowerCase()) {
            case "customer":
                if (customerRepository.getById(userId) == null) return false;
                customerRepository.lockAccount(userId);
                return true;
            case "restaurant":
                if (restaurantRepository.getById(userId) == null) return false;
                restaurantRepository.lockAccount(userId);
                return true;
            case "shipper":
                if (shipperRepository.getById(userId) == null) return false;
                shipperRepository.lockAccount(userId);
                return true;
        }
        return false;
    }

    @Override
    public boolean unlockAccount(Integer userId, String accountType) {
        switch (accountType.toLowerCase()) {
            case "customer":
                if (customerRepository.getById(userId) == null) return false;
                customerRepository.unlockAccount(userId);
                return true;
            case "restaurant":
                if (restaurantRepository.getById(userId) == null) return false;
                restaurantRepository.unlockAccount(userId);
                return true;
            case "shipper":
                if (shipperRepository.getById(userId) == null) return false;
                shipperRepository.unlockAccount(userId);
                return true;
        }
        return false;
    }


    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAll();
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.getAll();
    }

    @Override
    public List<Shipper> getAllShippers() {
        return shipperRepository.getAll();
    }

    @Override
    public boolean deleteAccount(Integer userId, String accountType) {
        try{
            switch(accountType.toLowerCase()){
                case "customer":
                    customerRepository.delete(userId);
                    return true;
                case "restaurant":
                    restaurantRepository.delete(userId);
                    return true;
                case "shipper":
                    shipperRepository.delete(userId);
                    return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public void viewSystem() {
        System.out.println("=== THỐNG KÊ HỆ THỐNG ===");
        System.out.println("Tổng số khách hàng: " + customerRepository.getAll().size());
        System.out.println("Tổng số nhà hàng: " + restaurantRepository.getAll().size());
        System.out.println("Tổng số shipper: " + shipperRepository.getAll().size());
    }
}
