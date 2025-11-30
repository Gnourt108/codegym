package casestudy_module2.RestaurantManagement.view;

import casestudy_module2.RestaurantManagement.entity.Customer;
import casestudy_module2.RestaurantManagement.entity.Restaurant;
import casestudy_module2.RestaurantManagement.entity.Shipper;
import casestudy_module2.RestaurantManagement.service.AdminServices.AdminService;
import casestudy_module2.RestaurantManagement.service.CartServices.CartService;
import casestudy_module2.RestaurantManagement.service.CustomerServices.CustomerService;
import casestudy_module2.RestaurantManagement.service.DishServices.DishService;
import casestudy_module2.RestaurantManagement.service.OrderServices.OrderService;
import casestudy_module2.RestaurantManagement.service.RestaurantServices.RestaurantService;
import casestudy_module2.RestaurantManagement.service.ShipperServices.ShipperService;
import common.InputHelper;

public class MainView {
    private final CustomerService customerService;
    private final RestaurantService restaurantService;
    private final ShipperService shipperService;
    private final DishService dishService;
    private final CartService cartService;
    private final OrderService orderService;
    private final AdminService adminService;

    private Customer currentCustomer;
    private Restaurant currentRestaurant;
    private Shipper currentShipper;

    public MainView(CustomerService customerService, RestaurantService restaurantService,
                    ShipperService shipperService, DishService dishService,
                    CartService cartService, OrderService orderService,
                    AdminService adminService) {
        this.customerService = customerService;
        this.restaurantService = restaurantService;
        this.shipperService = shipperService;
        this.dishService = dishService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.adminService = adminService;
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║   HỆ THỐNG ĐẶT ĐỒ ĂN ONLINE            ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice = InputHelper.inputInt("Nhập lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    showLoginMenu();
                    break;
                case 2:
                    showRegisterMenu();
                    break;
                case 0:
                    System.out.println("Thank you, next!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void showRegisterMenu() {
        System.out.println("\n=== ĐĂNG KÝ ===");
        System.out.println("1. Đăng ký Khách hàng");
        System.out.println("2. Đăng ký Nhà hàng");
        System.out.println("3. Đăng ký Shipper");
        System.out.println("0. Quay lại");
        System.out.print("Chọn: ");

        int choice = InputHelper.inputInt("Nhập lựa chọn của bạn: ");
        switch (choice) {
            case 1:
                registerCustomer();
                break;
            case 2:
                registerRestaurant();
                break;
            case 3:
                registerShipper();
                break;
        }
    }

    private void registerShipper() {
        System.out.println("Đăng kí shipper");
        String username = InputHelper.inputString("Tên đăng nhập: ");
        String password = InputHelper.inputString("Nhập mật khẩu: ");
        String phone = InputHelper.inputString("Nhập số điện thoại: ");
        String address = InputHelper.inputString("Nhập địa chỉ: ");
        String carType = InputHelper.inputString("Nhập loại xe: ");

        Shipper shipper = new Shipper(null, username, password, phone, address, false,
                false, carType, null, false, null);

        if (shipperService.register(shipper)) {
            System.out.println("Đăng ký thành công!");
        } else {
            System.out.println("Đăng ký thất bại!");
        }
    }

    private void registerRestaurant() {
        System.out.println("Đăng kí nhà hàng");
        String username = InputHelper.inputString("Tên đăng nhập: ");
        String password = InputHelper.inputString("Nhập mật khẩu: ");
        String phone = InputHelper.inputString("Nhập số điện thoại: ");
        String address = InputHelper.inputString("Nhập địa chỉ nhà hàng: ");
        String hostName = InputHelper.inputString("Nhập tên chủ nhà hàng: ");

        Restaurant restaurant = new Restaurant(null, username, password, phone, address, false);
        restaurant.setHostRestaurant(hostName);

        if (restaurantService.register(restaurant)) {
            System.out.println("Đăng ký thành công!");
        } else {
            System.out.println("Đăng ký thất bại!");
        }
    }

    private void registerCustomer() {
        System.out.println("Đăng kí khách hàng");
        String name = InputHelper.inputString("Tên đăng nhập: ");
        String password = InputHelper.inputString("Nhập mật khẩu: ");
        String phone = InputHelper.inputString("Nhập số điện thoại: ");
        String address = InputHelper.inputString("Nhập địa chỉ: ");
        String email = InputHelper.inputString("Nhập email: ");

        Customer customer = new Customer(null, name, password, phone, address,false);
        customer.setEmail(email);
        customer.setCurrentRevenue(1000000.0);
        if(customerService.register(customer)){
            System.out.println("Đăng kí tài khoản thành công!");
        }else{
            System.out.println("Đăng kí tài khoản thất bại!");
        }
    }

    private void showLoginMenu() {
        System.out.println("\n=== ĐĂNG NHẬP ===");
        System.out.println("1. Đăng nhập Admin");
        System.out.println("2. Đăng nhập Khách hàng");
        System.out.println("3. Đăng nhập Nhà hàng");
        System.out.println("4. Đăng nhập Shipper");
        System.out.println("0. Quay lại");
        System.out.print("Chọn: ");

        int choice = InputHelper.inputInt("Nhập lựa chọn của bạn: ");
        String username = InputHelper.inputString("Tên đăng nhập: ");
        String password =InputHelper.inputString("Nhập mật khẩu: ");

        switch (choice){
            case 1:
                if("admin".equals(username) && "admin123".equals(password)){
                    new AdminView(adminService, customerService, restaurantService, shipperService, dishService).showMenu();
                }else{
                    System.out.println("Sai tên đăng nhập hoặc mật khẩu!");
                }
                break;
            case 2:
                if (customerService.login(username, password)) {
                    currentCustomer = customerService.getByUsername(username);
                    new CustomerView(currentCustomer, dishService, cartService,
                            orderService, customerService).showMenu();
                } else {
                    System.out.println("Đăng nhập thất bại!");
                }
                break;
            case 3:
                if (restaurantService.login(username, password)) {
                    currentRestaurant = restaurantService.getByUsername(username);
                    new RestaurantView(currentRestaurant, dishService, restaurantService).showMenu();
                } else {
                    System.out.println("Đăng nhập thất bại!");
                }
                break;
            case 4:
                if (shipperService.login(username, password)) {
                    currentShipper = shipperService.getByUsername(username);
                    new ShipperView(currentShipper, orderService, shipperService).showMenu();
                } else {
                    System.out.println("Đăng nhập thất bại!");
                }
                break;
        }
    }
    
    

}
