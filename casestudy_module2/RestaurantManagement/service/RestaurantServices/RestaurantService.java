package casestudy_module2.RestaurantManagement.service.RestaurantServices;

import casestudy_module2.RestaurantManagement.entity.Dish;
import casestudy_module2.RestaurantManagement.entity.Restaurant;
import casestudy_module2.RestaurantManagement.repository.RestaurantRepository;

import java.util.HashMap;
import java.util.List;

public class RestaurantService implements IRestaurantService{

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }


    @Override
    public boolean register(Restaurant restaurant) {
        if (restaurant == null || restaurant.getUserName() == null || restaurant.getPassword() == null) {
            return false;
        }

        if (repository.getByUsername(restaurant.getUserName()) != null) {
            return false;
        }

        String password = restaurant.getPassword();
        String phone    = restaurant.getPhoneNumber();

        boolean isUpper = password.matches(".*[A-Z].*");
        boolean isLower = password.matches(".*[a-z].*");
        boolean isDigit = password.matches(".*[0-9].*");
        boolean isSpecial = password.matches(".*[!@#$%^&*()_+-=].*");
        boolean isMinLength = password.length() >= 8;

        if (!(isUpper && isLower && isDigit && isSpecial && isMinLength)) {
            System.out.println("Mật khẩu không đủ mạnh! Yêu cầu:");
            System.out.println("- Tối thiểu 8 ký tự");
            System.out.println("- Có ít nhất 1 chữ hoa (A-Z)");
            System.out.println("- Có ít nhất 1 chữ thường (a-z)");
            System.out.println("- Có ít nhất 1 số (0-9)");
            System.out.println("- Có ít nhất 1 ký tự đặc biệt: ! @ # $ % ^ & * ( ) _ + - =");
            System.out.println("Ví dụ: Abc123!@# hoặc MyPass99$");
            return false;
        }

        if(!phone.matches("^0[0-9]{9}$")){
            System.out.println("Số điện thoại phải bắt đầu bằng 0 và có đúng 10 chữ số!");
            System.out.println("Ví dụ hợp lệ: 0901234567, 0388889999, 0321112223");
            return false;
        }

        repository.add(restaurant);
        return true;
    }

    @Override
    public boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }

        Restaurant restaurant = repository.getByUsername(username);

        if (restaurant == null) {
            return false;
        }

        if (restaurant.isLocked()) {
            System.out.println("Tài khoản đã bị khóa!");
            return false;
        }

        return restaurant.getPassword().equals(password);
    }

    @Override
    public boolean changePassword(Integer restaurantId, String oldPassword, String newPassword) {
        if (restaurantId == null || oldPassword == null || newPassword == null) {
            return false;
        }

        Restaurant restaurant = repository.getById(restaurantId);
        if (restaurant == null) {
            return false;
        }

        if (!restaurant.getPassword().equals(oldPassword)) {
            return false;
        }

        if (newPassword.length() < 6) {
            return false;
        }

        restaurant.setPassword(newPassword);
        repository.update(restaurant);
        return true;
    }

    @Override
    public boolean updateProfile(Restaurant restaurant) {
        if (restaurant == null || restaurant.getIdUser() == null) {
            return false;
        }

        if (repository.getById(restaurant.getIdUser()) == null) {
            return false;
        }

        repository.update(restaurant);
        return true;
    }

    @Override
    public Restaurant getById(Integer restaurantId) {
        if (restaurantId == null) {
            return null;
        }
        return repository.getById(restaurantId);
    }

    @Override
    public Restaurant getByUsername(String username) {
        if (username == null) {
            return null;
        }
        return repository.getByUsername(username);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return repository.getAll();
    }

    @Override
    public List<Restaurant> searchByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return getAllRestaurants();
        }
        return repository.searchByUsername(username);
    }

    @Override
    public boolean isAccountLocked(Integer restaurantId) {
        Restaurant restaurant = repository.getById(restaurantId);
        return restaurant != null && restaurant.isLocked();
    }
}
