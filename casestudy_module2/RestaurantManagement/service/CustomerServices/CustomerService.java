package casestudy_module2.RestaurantManagement.service.CustomerServices;

import casestudy_module2.RestaurantManagement.entity.Customer;
import casestudy_module2.RestaurantManagement.repository.CustomerRepository;

import java.util.List;
public class CustomerService implements ICustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean register(Customer customer) {
        if (customer == null || customer.getUserName() == null || customer.getPassword() == null) {
            System.out.println("Thông tin không được để trống!");
            return false;
        }

        String username = customer.getUserName();
        String password = customer.getPassword();
        String email    = customer.getEmail();
        String phone    = customer.getPhoneNumber();

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

        String emailRegex = "^[A-Za-z0-9]+@gmail\\.com$";
        if(!email.matches(emailRegex)){
            System.out.println("Email không đúng định dạng! Ví dụ: abc123@example.com");
            return false;
        }

        if(!phone.matches("^0[0-9]{9}$")){
            System.out.println("Số điện thoại phải bắt đầu bằng 0 và có đúng 10 chữ số!");
            System.out.println("Ví dụ hợp lệ: 0901234567, 0388889999, 0321112223");
            return false;
        }

        repository.add(customer);
        return true;
    }

    @Override
    public boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }

        Customer customer = repository.getByUsername(username);

        if (customer == null) {
            return false;
        }

        if (customer.isLocked()) {
            System.out.println("Tài khoản đã bị khóa!");
            return false;
        }

        if (!customer.getPassword().equals(password)) {
            repository.incrementLoginAttempts(username);
            int attempts = repository.getLoginAttempts(username);

            if (attempts >= 3) {
                repository.lockAccount(customer.getIdUser());
                System.out.println("Tài khoản đã bị khóa do đăng nhập sai quá 3 lần!");
                return false;
            }

            System.out.println("Sai mật khẩu! Còn " + (3 - attempts) + " lần thử.");
            return false;
        }

        repository.resetLoginAttempts(username);
        return true;
    }

    @Override
    public boolean changePassword(Integer customerId, String oldPassword, String newPassword) {
        if (customerId == null || oldPassword == null || newPassword == null) {
            return false;
        }

        Customer customer = repository.getById(customerId);
        if (customer == null) {
            return false;
        }

        if (!customer.getPassword().equals(oldPassword)) {
            return false;
        }

        if (newPassword.length() < 6) {
            System.out.println("Mật khẩu phải có ít nhất 6 ký tự!");
            return false;
        }

        customer.setPassword(newPassword);
        repository.update(customer);
        return true;
    }

    @Override
    public boolean updateProfile(Customer customer) {
        if (customer == null || customer.getIdUser() == null) {
            return false;
        }

        if (repository.getById(customer.getIdUser()) == null) {
            return false;
        }

        repository.update(customer);
        return true;
    }

    @Override
    public Customer getById(Integer customerId) {
        if (customerId == null) {
            return null;
        }
        return repository.getById(customerId);
    }

    @Override
    public Customer getByUsername(String username) {
        if (username == null) {
            return null;
        }
        return repository.getByUsername(username);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repository.getAll();
    }

    @Override
    public List<Customer> searchByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return getAllCustomers();
        }
        return repository.searchByUsername(username);
    }

    @Override
    public boolean deposit(Integer customerId, double amount) {
        if (customerId == null || amount <= 0) {
            return false;
        }

        Customer customer = repository.getById(customerId);
        if (customer == null) {
            return false;
        }

        customer.setCurrentRevenue(customer.getCurrentRevenue() + amount);
        repository.update(customer);
        return true;
    }

    @Override
    public boolean withdraw(Integer customerId, double amount) {
        if (customerId == null || amount <= 0) {
            return false;
        }

        Customer customer = repository.getById(customerId);
        if (customer == null) {
            return false;
        }

        if (customer.getCurrentRevenue() < amount) {
            return false;
        }

        customer.setCurrentRevenue(customer.getCurrentRevenue() - amount);
        repository.update(customer);
        return true;
    }

    @Override
    public double getBalance(Integer customerId) {
        Customer customer = repository.getById(customerId);
        return customer != null ? customer.getCurrentRevenue() : 0;
    }

    @Override
    public boolean isAccountLocked(Integer customerId) {
        Customer customer = repository.getById(customerId);
        return customer != null && customer.isLocked();
    }

    @Override
    public int getLoginAttempts(String username) {
        return repository.getLoginAttempts(username);
    }
}
