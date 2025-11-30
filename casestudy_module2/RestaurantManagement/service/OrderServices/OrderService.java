package casestudy_module2.RestaurantManagement.service.OrderServices;

import casestudy_module2.RestaurantManagement.entity.*;
import casestudy_module2.RestaurantManagement.repository.CartRepository;
import casestudy_module2.RestaurantManagement.repository.CustomerRepository;
import casestudy_module2.RestaurantManagement.repository.DishRepository;
import casestudy_module2.RestaurantManagement.repository.OderRepository;
import casestudy_module2.RestaurantManagement.service.CartServices.ICartService;

import java.util.ArrayList;
import java.util.EventListenerProxy;
import java.util.List;

public class OrderService implements IOrderService{
    private final OderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final DishRepository dishRepository;
    private final CartRepository cartRepository;
    private final ICartService cartService;

    public OrderService(OderRepository orderRepository, CustomerRepository customerRepository, DishRepository dishRepository, CartRepository cartRepository, ICartService cartService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.dishRepository = dishRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
    }

    @Override
    public String placeOrderFromCart(Integer customerId, Integer shipperId) {
        Customer customer= customerRepository.getById(customerId);
        if (customer == null) {
            return "Không tìm thấy khách hàng!";
        }

        Cart cart = cartService.getCart(customerId);
        if(cart.getItems().isEmpty()){
            return "Giỏ hàng trống";
        }

        for(CartItem item : cart.getItems()){
            Dish dish = dishRepository.getDishById(item.getDishId());
            if(dish == null){
                return "Món "+item.getDishName()+" không tồn tại";
            }
            if(dish.getQuality() < item.getQuantity()){
                return String.format("Món %s chỉ còn %d phần, bạn đặt %d phần!",
                        item.getDishName(), dish.getQuality(), item.getQuantity());
            }
        }

        double total = cart.getTotalPrice();

        if(!canPlaceOrder(customerId, total)){
            return String.format("Số dư không đủ! Cần: %.2f VNĐ, Hiện có: %.2f VNĐ",
                    total, customer.getCurrentRevenue());
        }

        Order order = new Order();
        List<Dish> orderDishes = new ArrayList<>();

        for (CartItem item : cart.getItems()){
            Dish dish = dishRepository.getDishById(item.getDishId());

            Dish orderDish = new Dish();
            orderDish.setIdDish(dish.getIdDish());
            orderDish.setNameDish(dish.getNameDish());
            orderDish.setPrice(item.getDishPrice());
            orderDish.setQuality(item.getQuantity());
            orderDishes.add(orderDish);

            dish.setQuality(dish.getQuality() - item.getQuantity());
            dishRepository.updateDish(dish);
        }
        order.setCustomerId(customerId);
        order.setShipperId(shipperId);
        order.setDishes(orderDishes);
        order.setStatus("PENDING");
        order.setPay(true);

        orderRepository.add(order);

        customer.setCurrentRevenue(customer.getCurrentRevenue() - total);
        customerRepository.update(customer);

        cartService.clearCart(customerId);
        return "Đặt hàng thành công! Mã đơn hàng: "+order.getOrderID();
    }


    @Override
    public boolean cancelOrder(Integer customerId, Integer orderId) {
        if(!canCancelOrder(orderId)){
            return false;
        }

        Order order = orderRepository.getById(orderId);
        if(order == null){
            return false;
        }

        order.setStatus("CANCELLED");
        orderRepository.update(order);

        for (Dish orderDish : order.getDishes()){
            Dish dish = dishRepository.getDishById(orderDish.getIdDish());
            if(dish != null){
                dish.setQuality(dish.getQuality() + orderDish.getQuality());
                dishRepository.updateDish(dish);
            }
        }

        if(order.isPay()){
            Customer customer = customerRepository.getById(customerId);
            if(customer != null){
                double refund = 0;
                for (Dish dish : order.getDishes()){
                    refund += dish.getPrice() * dish.getQuality();
                }

                customer.setCurrentRevenue(customer.getCurrentRevenue() + refund);
                customerRepository.update(customer);
            }
        }
        return  true;
    }

    @Override
    public boolean updateOrderStatus(Integer orderId, String status) {
        if (orderId == null || status == null) {
            return false;
        }

        Order order = orderRepository.getById(orderId);
        if (order == null) {
            return false;
        }

        order.setStatus(status);
        orderRepository.update(order);
        return true;
    }

    @Override
    public boolean payOrder(Integer customerId, Integer orderId) {
        Customer customer = customerRepository.getById(customerId);
        Order order = orderRepository.getById(orderId);

        if (customer == null || order == null) {
            return false;
        }

        if (order.isPay()) {
            return false;
        }

        double total = 0;
        for (Dish dish : order.getDishes()){
            total += dish.getPrice() * dish.getQuality();
        }
        if(customer.getCurrentRevenue() < total){
            return false;
        }

        customer.setCurrentRevenue(customer.getCurrentRevenue() - total);

        order.setPay(true);
        customerRepository.update(customer);
        orderRepository.update(order);
        return true;
    }

    @Override
    public boolean checkPaymentStatus(Integer orderId) {
        Order order = orderRepository.getById(orderId);
        return order != null && order.isPay();
    }

    @Override
    public Order getOrderById(Integer orderId) {
        if (orderId == null) {
            return null;
        }
        return orderRepository.getById(orderId);
    }

    @Override
    public List<Order> getPendingOrders() {
        return orderRepository.getPendingOrders();
    }

    @Override
    public List<Order> getOrdersByStatus(String status) {
        if(status == null){
            return null;
        }

        List<Order> allOrders = orderRepository.getAll();
        List<Order> result = new ArrayList<>();

        for (Order o : allOrders){
            if(status.equals(o.getStatus())){
                result.add(o);
            }
        }
        return result;
    }

    @Override
    public boolean canPlaceOrder(Integer customerId, double totalAmount) {
        Customer customer = customerRepository.getById(customerId);
        if(customer == null){
            return false;
        }

        if(customer.isLocked()){
            return false;
        }
        return customer.getCurrentRevenue() >= totalAmount;
    }

    @Override
    public boolean canCancelOrder(Integer orderId) {
        Order order = orderRepository.getById(orderId);
        if(order == null){
            return false;
        }

        String status = order.getStatus();

        return "PENDING".equals(status) || "CONFIRMED".equals(status);
    }
}
