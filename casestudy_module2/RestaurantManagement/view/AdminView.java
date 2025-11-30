package casestudy_module2.RestaurantManagement.view;

import casestudy_module2.RestaurantManagement.entity.Customer;
import casestudy_module2.RestaurantManagement.entity.Restaurant;
import casestudy_module2.RestaurantManagement.entity.Shipper;
import casestudy_module2.RestaurantManagement.service.AdminServices.AdminService;
import casestudy_module2.RestaurantManagement.service.CustomerServices.CustomerService;
import casestudy_module2.RestaurantManagement.service.DishServices.DishService;
import casestudy_module2.RestaurantManagement.service.RestaurantServices.RestaurantService;
import casestudy_module2.RestaurantManagement.service.ShipperServices.ShipperService;
import common.InputHelper;

import java.util.List;

public class AdminView {
    private final AdminService adminService;
    private final CustomerService customerService;
    private final RestaurantService restaurantService;
    private final ShipperService shipperService;
    private final DishService dishService;

    public AdminView(AdminService adminService, CustomerService customerService,
                     RestaurantService restaurantService, ShipperService shipperService,
                     DishService dishService) {
        this.adminService = adminService;
        this.customerService = customerService;
        this.restaurantService = restaurantService;
        this.shipperService = shipperService;
        this.dishService = dishService;
    }

    public void showMenu(){
        while (true){
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║         QUẢN TRỊ HỆ THỐNG            ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("1. Quản lý khách hàng");
            System.out.println("2. Quản lý nhà hàng");
            System.out.println("3. Quản lý shipper");
            System.out.println("0. Đăng xuất");
            System.out.print("Chọn: ");

            int choice = InputHelper.inputInt("Nhập lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    manageCustomers();
                    break;
                case 2:
                    manageRestaurants();
                    break;
                case 3:
                    manageShippers();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void manageCustomers() {
        while (true) {
            System.out.println("\n=== QUẢN LÝ KHÁCH HÀNG ===");
            System.out.println("1. Xem danh sách khách hàng");
            System.out.println("2. Tìm kiếm khách hàng");
            System.out.println("3. Khóa tài khoản");
            System.out.println("4. Mở khóa tài khoản");
            System.out.println("5. Xóa tài khoản");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            int choice = InputHelper.inputInt("Nhập lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    viewAllCustomers();
                    break;
                case 2:
                    searchCustomers();
                    break;
                case 3:
                    lockAccount("customer");
                    break;
                case 4:
                    unlockAccount("customer");
                    break;
                case 5:
                    deleteAccount("customer");
                    break;
                case 0:
                    return;
            }
        }
    }

    private void deleteAccount(String type) {
        int id = InputHelper.inputInt("Nhập Id tài khoản cần xóa: ");
        String confirm = InputHelper.inputString("Xác nhận xóa (y/n): ");
        
        if(confirm.equalsIgnoreCase("Y")){
            if(adminService.deleteAccount(id, type)){
                System.out.println("Đã xóa tài khoản thành công!");
            }else{
                System.out.println("Xóa tài khoản thất bại");
            }
        }
    }

    private void unlockAccount(String type) {
        int id = InputHelper.inputInt("Nhập ID tài khoản cần mở khóa: ");
        if(adminService.unlockAccount(id, type)){
            System.out.println("Đã mở khóa tài khoản");
        }else{
            System.out.println("ID không tồn tại");
        }

    }

    private void lockAccount(String type) {
        int id = InputHelper.inputInt("Nhập ID tài khoản cần khóa: ");
        if(adminService.lockAccount(id, type)){
            System.out.println("Đã khóa tài khoản");
        }else{
            System.out.println("ID không tồn tại");
        }
    }

    private void searchCustomers() {
        String username = InputHelper.inputString("Nhập tên đăng nhập: ");
        List<Customer> customers = customerService.searchByUsername(username);
        if (customers.isEmpty()) {
            System.out.println("Không tìm thấy!");
            return;
        }

        for (Customer c : customers) {
            System.out.printf("ID: %d | %s | %s\n",
                    c.getIdUser(), c.getUserName(), c.getPhoneNumber());
        }
    }

    private void viewAllCustomers() {
        List<Customer> customers = adminService.getAllCustomers();
        if(customers.isEmpty()){
            System.out.println("Chưa có khách hàng nào!");
            return;
        }
        System.out.println("\n=== DANH SÁCH KHÁCH HÀNG ===");
        System.out.printf("%-5s %-20s %-15s %-30s %-10s\n",
                "ID", "Tên đăng nhập", "SĐT", "Email", "Trạng thái");
        System.out.println("─".repeat(90));

        for (Customer c : customers){
            String status = c.isLocked() ? "Khóa" : "Hoạt động";
            System.out.printf("%-5d %-20s %-15s %-30s %-10s\n",
                    c.getIdUser(), c.getUserName(), c.getPhoneNumber(),
                    c.getEmail() != null ? c.getEmail() : "N/A", status);
        }
    }

    private void manageRestaurants() {
        while (true) {
            System.out.println("\n=== QUẢN LÝ NHÀ HÀNG ===");
            System.out.println("1. Xem danh sách nhà hàng");
            System.out.println("2. Tìm kiếm nhà hàng");
            System.out.println("3. Khóa tài khoản");
            System.out.println("4. Mở khóa tài khoản");
            System.out.println("5. Xóa tài khoản");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            int choice = InputHelper.inputInt("Nhập lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    viewAllRestaurants();
                    break;
                case 2:
                    searchRestaurants();
                    break;
                case 3:
                    lockAccount("restaurant");
                    break;
                case 4:
                    unlockAccount("restaurant");
                    break;
                case 5:
                    deleteAccount("restaurant");
                    break;
                case 0:
                    return;
            }
        }
    }

    private void searchRestaurants() {
        String username = InputHelper.inputString("Nhập tên đăng nhập: ");
        List<Restaurant> restaurants = restaurantService.searchByUsername(username);

        if (restaurants.isEmpty()) {
            System.out.println("Không tìm thấy!");
            return;
        }

        for (Restaurant r : restaurants) {
            System.out.printf("ID: %d | %s | %s\n",
                    r.getIdUser(), r.getUserName(), r.getPhoneNumber());
        }
    }

    private void viewAllRestaurants() {
        List<Restaurant> restaurants = adminService.getAllRestaurants();
        if (restaurants.isEmpty()) {
            System.out.println("Chưa có nhà hàng nào!");
            return;
        }

        System.out.println("\n=== DANH SÁCH NHÀ HÀNG ===");
        System.out.printf("%-5s %-20s %-15s %-30s %-10s\n",
                "ID", "Tên đăng nhập", "SĐT", "Địa chỉ", "Trạng thái");
        System.out.println("─".repeat(90));

        for (Restaurant r : restaurants) {
            String status = r.isLocked() ? "Khóa" : "Hoạt động";
            System.out.printf("%-5d %-20s %-15s %-30s %-10s\n",
                    r.getIdUser(), r.getUserName(), r.getPhoneNumber(),
                    r.getAddress(), status);
        }
    }

    private void manageShippers() {
        while (true) {
            System.out.println("\n=== QUẢN LÝ SHIPPER ===");
            System.out.println("1. Xem danh sách shipper");
            System.out.println("2. Tìm kiếm shipper");
            System.out.println("3. Khóa tài khoản");
            System.out.println("4. Mở khóa tài khoản");
            System.out.println("5. Xóa tài khoản");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            int choice = InputHelper.inputInt("Nhập lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    viewAllShippers();
                    break;
                case 2:
                    searchShippers();
                    break;
                case 3:
                    lockAccount("shipper");
                    break;
                case 4:
                    unlockAccount("shipper");
                    break;
                case 5:
                    deleteAccount("shipper");
                    break;
                case 0:
                    return;
            }
        }
    }

    private void searchShippers() {
        String name = InputHelper.inputString("Nhập tên đăng nhập: ");
        List<Shipper> shippers = shipperService.searchByUsername(name);
        if (shippers.isEmpty()) {
            System.out.println("Không tìm thấy!");
            return;
        }

        for (Shipper s : shippers) {
            System.out.printf("ID: %d | %s | %s\n",
                    s.getIdUser(), s.getUserName(), s.getPhoneNumber());
        }
    }

    private void viewAllShippers() {
        List<Shipper> shippers = shipperService.getAllShippers();
        if(shippers.isEmpty()){
            System.out.println("Chưa có shipper nào!");
            return;
        }

        System.out.println("\n=== DANH SÁCH SHIPPER ===");
        System.out.printf("%-5s %-20s %-15s %-15s %-10s\n",
                "ID", "Tên đăng nhập", "SĐT", "Loại xe", "Trạng thái");
        System.out.println("─".repeat(75));

        for (Shipper s : shippers) {
            String status = s.isLocked() ? "Khóa" : (s.isBusy() ? "Bận" : "Rảnh");
            System.out.printf("%-5d %-20s %-15s %-15s %-10s\n",
                    s.getIdUser(), s.getUserName(), s.getPhoneNumber(), s.getCarType(), status);

        }
    }
}
