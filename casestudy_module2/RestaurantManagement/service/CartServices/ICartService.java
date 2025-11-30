package casestudy_module2.RestaurantManagement.service.CartServices;

import casestudy_module2.RestaurantManagement.entity.Cart;
import casestudy_module2.RestaurantManagement.entity.Dish;

import java.util.List;

public interface ICartService {

    Cart getCart(Integer customerId);
    boolean addToCart(Integer customerId, Integer dishId, Integer quantity);
    boolean removeFromCart(Integer customerId, Integer dishId);
    boolean updateCartItemQuantity(Integer customerId, Integer dishId, Integer newQuantity);
    boolean clearCart(Integer customerId);

    boolean increaseQuantity(Integer customerId, Integer dishId, Integer amount);
    boolean decreaseQuantity(Integer customerId, Integer dishId, Integer amount);

    Cart applyDiscount(Integer customerId, double discountPercent);
    boolean isCartEmpty(Integer customerId);
    int getCartItemCount(Integer customerId);
}
