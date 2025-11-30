package casestudy_module2.RestaurantManagement.service.OrderServices;

import casestudy_module2.RestaurantManagement.entity.Order;
import casestudy_module2.RestaurantManagement.entity.Shipper;

import java.util.List;

public interface IOrderService {
    String placeOrderFromCart(Integer customerId, Integer shipperId);

    boolean cancelOrder(Integer customerId, Integer orderId);
    boolean updateOrderStatus(Integer orderId, String status);

    boolean payOrder(Integer customerId, Integer orderId);
    boolean checkPaymentStatus(Integer orderId);

    Order getOrderById(Integer orderId);
    List<Order> getPendingOrders();
    List<Order> getOrdersByStatus(String status);

    boolean canPlaceOrder(Integer customerId, double totalAmount);
    boolean canCancelOrder(Integer orderId);
}
