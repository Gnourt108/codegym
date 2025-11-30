package casestudy_module2.RestaurantManagement.view;

import casestudy_module2.RestaurantManagement.entity.Dish;
import casestudy_module2.RestaurantManagement.entity.Restaurant;
import casestudy_module2.RestaurantManagement.service.DishServices.DishService;
import casestudy_module2.RestaurantManagement.service.RestaurantServices.RestaurantService;
import common.InputHelper;

import javax.crypto.interfaces.DHKey;
import java.util.List;

public class RestaurantView {

    private final Restaurant restaurant;
    private final DishService dishService;
    private final RestaurantService restaurantService;

    public RestaurantView(Restaurant restaurant, DishService dishService,
                          RestaurantService restaurantService) {
        this.restaurant = restaurant;
        this.dishService = dishService;
        this.restaurantService = restaurantService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║   NHÀ HÀNG: " + restaurant.getUserName());
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("1. Quản lý món ăn");
            System.out.println("2. Thêm món mới");
            System.out.println("3. Xóa món");
            System.out.println("4. Cập nhật món");
            System.out.println("5. Đặt giảm giá cho món");
            System.out.println("6. Đánh dấu món bán chạy");
            System.out.println("8. Đổi mật khẩu");
            System.out.println("0. Đăng xuất");
            System.out.print("Chọn: ");

            int choice = InputHelper.inputInt("Nhập lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    viewAllDishes();
                    break;
                case 2:
                    addDish();
                    break;
                case 3:
                    deleteDish();
                    break;
                case 4:
                    updateDish();
                    break;
                case 5:
                    setDiscount();
                    break;
                case 6:
                    markBestSeller();
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
        String oldPass = InputHelper.inputString("Nhập mật khẩu cũ: ");
        String newPass = InputHelper.inputString("Nhập mật khẩu mới: ");

        if (restaurantService.changePassword(restaurant.getIdUser(), oldPass, newPass)) {
            System.out.println("Đổi mật khẩu thành công!");
        } else {
            System.out.println("Đổi mật khẩu thất bại!");
        }
    }

    private void markBestSeller() {
        int id = InputHelper.inputInt("Nhập ID món: ");
        int mark = InputHelper.inputInt("Nhấn 1 để đánh dấu (0 để bỏ đánh dấu): ");
        boolean success = mark == 1 ?
                dishService.markAsBestSeller(id) :
                dishService.unmarkBestSeller(id);

        if (success) {
            System.out.println("Thành công!");
        } else {
            System.out.println("Thất bại!");
        }
    }

    private void setDiscount() {
        int id = InputHelper.inputInt("Nhập Id món: ");

        Dish dish = dishService.getDishById(id);
        if(dish == null){
            System.out.println("Không tìm thấy món với ID: "+id);
            return;
        }

        double percent = InputHelper.inputDouble("Nhập phần trăm giảm giá (0 - 100): ");

        if(percent < 0 || percent > 100){
            System.out.println("Phần trăm gim giá phải từ 0 -100!");
            return;
        }

        int days = InputHelper.inputInt("Số ngày có hiệu lực: ");
        if (days <= 0){
            System.out.println("Số ngày phải lớn hơn 0!");
            return;
        }

        double discountDecimal = percent / 100.0;

        if (dishService.setDiscount(id, discountDecimal, days)) {
            System.out.println("Đặt giảm giá thành công!");
            System.out.printf("Món '%s' giảm %.0f%% trong %d ngày\n",
                    dish.getNameDish(), percent, days);
        } else {
            System.out.println("Đặt giảm giá thất bại!");
        }
    }

    private void updateDish() {
        int id = InputHelper.inputInt("Nhập id món cần cập nhật: ");

        Dish dish = dishService.getDishById(id);
        if (dish == null) {
            System.out.println("Không tìm thấy món!");
            return;
        }

        System.out.println("Thông tin hiện tại: ");
        System.out.printf("Tên: %s | Giá: %.2f | Số lượng: %d\n",
                dish.getNameDish(), dish.getPrice(), dish.getQuality());

        String name = InputHelper.inputString("Nhập tên mới (Enter để giữ nguyên): ");
        if(!name.trim().isEmpty()){
            dish.setNameDish(name);
        }

        double price = InputHelper.inputDouble("Nhập giá mới (0 để giữ nguyên): ");
        if (price > 0) {
            dish.setPrice(price);
        }

        int quantity = InputHelper.inputInt("Nhập số lượng mới (-1 để giữ nguyên): ");
        if (quantity >= 0) {
            dish.setQuality(quantity);
        }

        if (dishService.updateDish(dish)) {
            System.out.println("Cập nhật thành công!");
        } else {
            System.out.println("Cập nhật thất bại!");
        }

    }

    private void deleteDish() {
        String name = InputHelper.inputString("Nhập tên món ăn cần xóa: ");
        List<Dish> dishes = dishService.searchDishesByName(name);

        if(dishes.isEmpty()){
            System.out.println("Không tìm thấy món có tên: "+name);
            return;
        }

        if (dishService.deleteDish(name)) {
            System.out.println("Xóa món thành công!");
        } else {
            System.out.println("Xóa món thất bại!");
        }
    }

    private void addDish() {
        System.out.println("\n === THÊM MÓN MỚI ===");
        String dishName = InputHelper.inputString("Nhập tên món: ");
        Double price = InputHelper.inputDouble("Nhập giá món: ");
        int quantity = InputHelper.inputInt("Nhập số lượng món: ");

        Dish dish = new Dish();
        dish.setNameDish(dishName);
        dish.setPrice(price);
        dish.setQuality(quantity);
        dish.setEndow(false);
        dish.setIsDiscount(0);
        dish.setBestSeller(false);
        dish.setDiscountDeadline(0);

        if (dishService.addDish(dish)) {
            System.out.println("Thêm món thành công!");
        } else {
            System.out.println("Thêm món thất bại!");
        }
    }

    private void viewAllDishes() {
        List<Dish> dishes = dishService.getAllDishes();
        if(dishes.isEmpty()){
            System.out.println("Chưa có món ăn nào!");
            return;
        }

        System.out.println("\n=== DANH SÁCH MÓN ĂN ===");
        System.out.printf("%-5s %-30s %-15s %-10s %-10s %-15s\n",
                "ID", "Tên món", "Giá", "Số lượng", "Giảm giá", "Bán chạy");
        System.out.println("─".repeat(90));

        for (Dish dish : dishes){
            String discount = dish.isEndow() && dish.getIsDiscount() > 0 ?
                    String.format("%.0f%%", dish.getIsDiscount() * 100) : "Không";
            String bestSeller = dish.isBestSeller() ? "Có" : "Không";

            System.out.printf("%-5d %-30s %-15.2f %-10d %-10s %-15s\n",
                    dish.getIdDish(), dish.getNameDish(), dish.getPrice(),
                    dish.getQuality(), discount, bestSeller);
        }
    }
}
