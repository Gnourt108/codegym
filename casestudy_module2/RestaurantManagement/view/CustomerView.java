package casestudy_module2.RestaurantManagement.view;

import casestudy_module2.RestaurantManagement.entity.*;
import casestudy_module2.RestaurantManagement.service.CartServices.CartService;
import casestudy_module2.RestaurantManagement.service.CartServices.ICartService;
import casestudy_module2.RestaurantManagement.service.CustomerServices.CustomerService;
import casestudy_module2.RestaurantManagement.service.CustomerServices.ICustomerService;
import casestudy_module2.RestaurantManagement.service.DishServices.DishService;
import casestudy_module2.RestaurantManagement.service.DishServices.IDishService;
import casestudy_module2.RestaurantManagement.service.OrderServices.IOrderService;
import casestudy_module2.RestaurantManagement.service.OrderServices.OrderService;
import common.InputHelper;

import java.util.List;
import java.util.Scanner;

public class CustomerView{

    private final Scanner scanner = new Scanner(System.in);
    private final Customer customer;
    private final DishService dishService;
    private final CartService cartService;
    private final OrderService orderService;
    private final CustomerService customerService;

    public CustomerView(Customer customer, DishService dishService, CartService cartService,
                        OrderService orderService, CustomerService customerService) {
        this.customer = customer;
        this.dishService = dishService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.customerService = customerService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║   KHÁCH HÀNG: " + customer.getUserName());
            System.out.println("║   Số dư: " + String.format("%.2f VNĐ", customer.getCurrentRevenue()));
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("1. Xem danh sách món ăn");
            System.out.println("2. Tìm kiếm món ăn");
            System.out.println("3. Xem món giảm giá");
            System.out.println("4. Xem món bán chạy");
            System.out.println("5. Xem giỏ hàng");
            System.out.println("6. Đặt hàng");
            System.out.println("7. Nạp tiền");
            System.out.println("8. Đổi mật khẩu");
            System.out.println("0. Đăng xuất");
            System.out.print("Chọn: ");

            int choice = InputHelper.inputInt("Nhập lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    viewAllDishes();
                    break;
                case 2:
                    searchDishes();
                    break;
                case 3:
                    viewDiscountedDishes();
                    break;
                case 4:
                    viewBestSellers();
                    break;
                case 5:
                    viewCart();
                    break;
                case 6:
                    placeOrder();
                    break;
                case 7:
                    deposit();
                    break;
                case 8:
                    changePassword();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void changePassword() {
        System.out.print("Mật khẩu cũ: ");
        String oldPass = scanner.nextLine();
        System.out.print("Mật khẩu mới: ");
        String newPass = scanner.nextLine();

        if (customerService.changePassword(customer.getIdUser(), oldPass, newPass)) {
            System.out.println("Đổi mật khẩu thành công!");
        } else {
            System.out.println("Đổi mật khẩu thất bại!");
        }
    }

    private void deposit() {
        double amount = InputHelper.inputDouble("Nhập số tiền cần nạp: ");
        if(customerService.deposit(customer.getIdUser(), amount)){
            Customer updatedCustomer = customerService.getById(customer.getIdUser());
            customer.setCurrentRevenue(updatedCustomer.getCurrentRevenue());
            System.out.printf("Nạp tiền thành công! Số dư hiện tại: %.2f VNĐ\n", customer.getCurrentRevenue());
        }else {
            System.out.println("Nạp tiền thất bại!");
        }
    }

    private void placeOrder() {
        Cart cart = cartService.getCart(customer.getIdUser());
        if(cart.getItems().isEmpty()){
            System.out.println("Giỏ hàng trống");
            return;
        }
        System.out.printf("Tổng tiền: %.2f VNĐ\n", cart.getTotalPrice());
        System.out.printf("Số dư hiện tại: %.2f VNĐ\n", customer.getCurrentRevenue());

        if(customer.getCurrentRevenue() < cart.getTotalPrice()){
            System.out.println("Số dư không đủ! Vui lòng nạp thêm tiền.");
            return;
        }

        String confirm = InputHelper.inputString("Xác nhận đặt hàng (y/n): ");
        if(confirm.equalsIgnoreCase("y")){
            String result = orderService.placeOrderFromCart(customer.getIdUser(), null);
            System.out.println(result);
            
            Customer updatedCustomer = customerService.getById(customer.getIdUser());
            customer.setCurrentRevenue(updatedCustomer.getCurrentRevenue());
        }
    }

    private void viewCart() {
        Cart cart = cartService.getCart(customer.getIdUser());
        if (cart.getItems().isEmpty()) {
            System.out.println("Giỏ hàng trống!");
            return;
        }
        System.out.println("\n === GIỎ HÀNG ===");
        System.out.printf("%-30s %-15s %-10s %-15s\n",
                "Tên món", "Giá", "Số lượng", "Tổng");
        System.out.println("─".repeat(75));

        for (CartItem item : cart.getItems()) {
            System.out.printf("%-30s %-15.2f %-10d %-15.2f\n",
                    item.getDishName(), item.getDishPrice(),
                    item.getQuantity(), item.getSubtotal());
        }

        System.out.println("─".repeat(75));
        System.out.printf("Tổng cộng: %.2f VNĐ\n", cart.getTotalPrice());

        System.out.println("\n1. Xóa món");
        System.out.println("2. Cập nhật số lượng");
        System.out.println("3. Xóa toàn bộ giỏ hàng");
        System.out.println("0. Quay lại");
        int choice = InputHelper.inputInt("Nhập lựa chọn của bạn: ");
        switch (choice){
            case 1:
                int dishId = InputHelper.inputInt("Nhập ID món cần xóa: ");
                if(cartService.removeFromCart(customer.getIdUser(), dishId)){
                    System.out.println("Đã xóa giỏ hàng thành công!");
                }
                break;
            case 2:
                int dishId1 = InputHelper.inputInt("Nhập ID món cần cập nhật số lượng: ");
                int quality = InputHelper.inputInt("Nhập số lượng mới: ");
                if(cartService.updateCartItemQuantity(customer.getIdUser(), dishId1, quality)){
                    System.out.println("Đã cập nhật thành công!");
                }
                break;
            case 3:
                cartService.clearCart(customer.getIdUser());
                System.out.println("Đã xóa giỏ hàng!");
                break;
        }
    }

    private void viewBestSellers() {
        List<Dish> dishes = dishService.getBestSellerDishes();
        if (dishes.isEmpty()) {
            System.out.println("Chưa có món bán chạy!");
            return;
        }

        System.out.println("\n=== MÓN BÁN CHẠY ===");
        for (Dish dish : dishes) {
            System.out.printf("%s - %.2f VNĐ\n", dish.getNameDish(),
                    dishService.calculateDiscountedPrice(dish));
        }
    }

    private void viewDiscountedDishes() {
        List<Dish> dishes = dishService.getDiscountedDishes();
        if(dishes.isEmpty()){
            System.out.println("Không có món ăn giảm giá!");
            return;
        }

        System.out.println("\n=== MÓN ĐANG GIẢM GIÁ ===");
        for (Dish dish : dishes){
            double originalPrice = dish.getPrice();
            double finalPrice = dishService.calculateDiscountedPrice(dish);
            System.out.printf("%s - Giá gốc: %.2f VNĐ → %.2f VNĐ (Giảm %.0f%%)\n",
                    dish.getNameDish(), originalPrice, finalPrice,
                    dish.getIsDiscount() * 100);
        }
    }

    private void searchDishes() {
        String name = InputHelper.inputString("Nhập tên món ăn cần tìm kiếm: ");
        List<Dish> dishes = dishService.searchDishesByName(name);

        if (dishes.isEmpty()) {
            System.out.println("Không tìm thấy món nào!");
            return;
        }

        System.out.println("\n=== KẾT QUẢ TÌM KIẾM ===");
        for (Dish dish : dishes) {
            System.out.printf("ID: %d | %s - %.2f VNĐ\n",
                    dish.getIdDish(), dish.getNameDish(),
                    dishService.calculateDiscountedPrice(dish));
        }
    }

    private void viewAllDishes() {
        List<Dish> dishes = dishService.getAllDishes();
        if(dishes.isEmpty()){
            System.out.println("Chưa có món ăn nào!");
            return;
        }

        System.out.println("\n=== DANH SÁCH MÓN ĂN ===");
        System.out.printf("%-5s %-30s %-15s %-10s %-15s\n",
                "ID", "Tên món", "Giá", "Số lượng", "Giảm giá");
        System.out.println("─".repeat(80));

        for (Dish dish : dishes){
            double finalPrice = dishService.calculateDiscountedPrice(dish);
            String discount = dish.isEndow() && dish.getIsDiscount() > 0 ?     String.format("%.0f%%", dish.getIsDiscount() * 100) : "Không";
            System.out.printf("%-5d %-30s %-15.2f %-10d %-15s\n",
                    dish.getIdDish(), dish.getNameDish(), finalPrice,
                    dish.getQuality(), discount);
        }

        System.out.println("\nThêm vào giỏ hàng? (Nhập ID để thêm / 0 để bỏ qua): ");
        int dishId = InputHelper.inputInt("Nhập mã món ăn: ");
        if(dishId > 0){
            int quality = InputHelper.inputInt("Nhập số lượng: ");
            if (cartService.addToCart(customer.getIdUser(), dishId, quality)){
                System.out.println("Đã thêm vào giỏ hàng");
            }else{
                System.out.println("Thêm vào giỏ hàng thất bại");
            }
        }
    }
}
