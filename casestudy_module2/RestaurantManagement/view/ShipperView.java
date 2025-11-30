package casestudy_module2.RestaurantManagement.view;

import casestudy_module2.RestaurantManagement.entity.Dish;
import casestudy_module2.RestaurantManagement.entity.Order;
import casestudy_module2.RestaurantManagement.entity.Shipper;
import casestudy_module2.RestaurantManagement.service.OrderServices.OrderService;
import casestudy_module2.RestaurantManagement.service.ShipperServices.ShipperService;
import common.InputHelper;

import java.util.List;

public class ShipperView {
    private final Shipper shipper;
    private final OrderService orderService;
    private final ShipperService shipperService;

    public ShipperView(Shipper shipper, OrderService orderService,
                       ShipperService shipperService) {
        this.shipper = shipper;
        this.orderService = orderService;
        this.shipperService = shipperService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║   SHIPPER: " + shipper.getUserName());
            System.out.println("║   Trạng thái: " + (shipper.isBusy() ? "Đang giao hàng" : "Sẵn sàng"));
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("1. Xem đơn hàng chờ giao");
            System.out.println("2. Nhận đơn hàng");
            System.out.println("3. Xem đơn hàng của tôi");
            System.out.println("4. Cập nhật trạng thái đơn hàng");
            System.out.println("5. Đổi trạng thái (Rảnh/Bận)");
            System.out.println("6. Đổi mật khẩu");
            System.out.println("0. Đăng xuất");
            System.out.print("Chọn: ");

            int choice = InputHelper.inputInt("Nhập lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    viewPendingOrders();
                    break;
                case 2:
                    acceptOrder();
                    break;
                case 3:
                    viewMyOrders();
                    break;
                case 4:
                    updateOrderStatus();
                    break;
                case 5:
                    toggleBusyStatus();
                    break;
                case 6:
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

        if (shipperService.changePassword(shipper.getIdUser(), oldPass, newPass)) {
            System.out.println("Đổi mật khẩu thành công!");
        } else {
            System.out.println("Đổi mật khẩu thất bại!");
        }

    }

    private void toggleBusyStatus() {
        boolean newStatus = !shipper.isBusy();
        shipperService.setShipperBusy(shipper.getIdUser(), newStatus);
        shipper.setBusy(newStatus);
        System.out.println("Trạng thái mới: " + (newStatus ? "Bận" : "Rảnh"));
    }

    private void updateOrderStatus() {
        int orderId = InputHelper.inputInt("Nhập mã đơn hàng: ");
        Order order = orderService.getOrderById(orderId);
        if(order == null){
            System.out.println("Không tìm thấy đơn hàng!");
            return;
        }
        System.out.println("\n=== TRẠNG THÁI ĐƠN HÀNG ===");
        System.out.println("1. Đang giao ");
        System.out.println("2. Đã giao ");
        System.out.println("3. Hủy");
        System.out.print("Chọn trạng thái mới: ");

        int choice = InputHelper.inputInt("Nhập lựa chọn của bạn: ");
        String newStatus = "";

        switch (choice) {
            case 1:
                newStatus = "SHIPPING";
                break;
            case 2:
                newStatus = "DELIVERED";
                shipperService.setShipperBusy(shipper.getIdUser(), false);
                shipper.setBusy(false);
                break;
            case 3:
                newStatus = "CANCELLED";
                shipperService.setShipperBusy(shipper.getIdUser(), false);
                shipper.setBusy(false);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
                return;
        }

        if (orderService.updateOrderStatus(orderId, newStatus)) {
            System.out.println("Cập nhật trạng thái thành công!");
        } else {
            System.out.println("Cập nhật thất bại!");
        }

    }

    private void viewMyOrders() {
        List<Order> orders = orderService.getOrdersByStatus("SHIPPING");
        if (orders.isEmpty()) {
            System.out.println("Bạn chưa có đơn hàng nào!");
            return;
        }
        System.out.println("\n=== ĐƠN HÀNG CỦA TÔI ===");
        for (Order order : orders) {
            System.out.printf("Mã đơn: %d | Trạng thái: %s\n",
                    order.getOrderID(), order.getStatus());
            System.out.println("Món ăn:");
            for (Dish dish : order.getDishes()) {
                System.out.printf("  - %s (x%d)\n", dish.getNameDish(), dish.getQuality());
            }
            System.out.println();
        }
    }

    private void acceptOrder() {
        if (shipper.isBusy()) {
            System.out.println("Bạn đang bận! Không thể nhận thêm đơn!");
            return;
        }

        int id = InputHelper.inputInt("Nhập mã đơn hàng muốn nhận: ");
        Order order = orderService.getOrderById(id);
        if(order == null){
            System.out.println("Không tìm thấy đơn hàng!");
            return;
        }

        if(!"PENDING".equals(order.getStatus())){
            System.out.println("Đơn hàng này không ở trạng thái chờ giao!");
            return;
        }

        orderService.updateOrderStatus(id, "SHIPPING");

        shipperService.setShipperBusy(shipper.getIdUser(), true);
        shipper.setBusy(true);

        System.out.println("Đã nhận đơn hàng thành công!");

    }

    private void viewPendingOrders() {
        List<Order> orders = orderService.getPendingOrders();
        if(orders.isEmpty()){
            System.out.println("Không có đơn hàng chờ giao!");
            return;
        }

        System.out.println("\n=== ĐƠN HÀNG CHỜ GIAO ===");
        System.out.printf("%-10s %-15s %-40s\n", "Mã đơn", "Trạng thái", "Món ăn");
        System.out.println("─".repeat(70));

        for (Order order : orders){
            StringBuilder dishes = new StringBuilder();
            for (Dish dish : order.getDishes()) {
                dishes.append(dish.getNameDish()).append(", ");
            }
            if (dishes.length() > 0) {
                dishes.setLength(dishes.length() - 2);
            }

            System.out.printf("%-10d %-15s %-40s\n",
                    order.getOrderID(), order.getStatus(), dishes.toString());
        }
    }
}
