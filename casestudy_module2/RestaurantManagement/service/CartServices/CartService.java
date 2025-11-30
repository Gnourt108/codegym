package casestudy_module2.RestaurantManagement.service.CartServices;

import casestudy_module2.RestaurantManagement.entity.Cart;
import casestudy_module2.RestaurantManagement.entity.CartItem;
import casestudy_module2.RestaurantManagement.entity.Dish;
import casestudy_module2.RestaurantManagement.repository.CartRepository;
import casestudy_module2.RestaurantManagement.repository.DishRepository;

import java.util.ArrayList;
import java.util.List;

public class CartService implements ICartService{

    private final CartRepository cartRepository;
    private final DishRepository dishRepository;

    public CartService(CartRepository cartRepository, DishRepository dishRepository) {
        this.cartRepository = cartRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public Cart getCart(Integer customerId) {
        List<CartItem> items = cartRepository.getCartItemsByCustomer(customerId);

        double totalPrice = 0;
        int totalQuality = 0;

        for (CartItem item : items){
            totalPrice += item.getSubtotal();
            totalQuality += item.getQuantity();
        }

        return new Cart(customerId, items, totalPrice, items.size(), totalQuality);
    }

    @Override
    public boolean addToCart(Integer customerId, Integer dishId, Integer quantity) {
        if (customerId == null || dishId == null || quantity == null || quantity <= 0) {
            return false;
        }

        Dish dish = dishRepository.getDishById(dishId);
        if (dish == null) {
            return false;
        }

        CartItem cartItem = cartRepository.getCartItem(customerId, dishId);

        if(cartItem != null){
            int newQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(newQuantity);

            double price = dishRepository.calculateDiscountedPrice(dish);
            cartItem.setSubtotal(price * newQuantity);

            cartRepository.saveCartItem(cartItem);
        }else{
            double price = dishRepository.calculateDiscountedPrice(dish);
            double subtotal = price * quantity;

            CartItem newItem = new CartItem(
                    customerId, dishId, dish.getNameDish(), price, quantity, subtotal
            );
            cartRepository.saveCartItem(newItem);
        }
        return true;
    }

    @Override
    public boolean removeFromCart(Integer customerId, Integer dishId) {
        if(customerId == null || dishId == null){
            return false;
        }

        cartRepository.deleteCartItem(customerId, dishId);
        return true;
    }

    @Override
    public boolean updateCartItemQuantity(Integer customerId, Integer dishId, Integer newQuantity) {
        if(customerId == null || dishId == null || newQuantity == null){
            return false;
        }

        if(newQuantity <= 0){
            return removeFromCart(customerId, dishId);
        }

        CartItem item = cartRepository.getCartItem(customerId, dishId);
        if(item == null){
            return false;
        }

        item.setQuantity(newQuantity);
        Dish dish = dishRepository.getDishById(dishId);
        if(dish != null){
            double price = dishRepository.calculateDiscountedPrice(dish);
            item.setSubtotal(price * newQuantity);
        }

        cartRepository.saveCartItem(item);
        return true;
    }

    @Override
    public boolean clearCart(Integer customerId) {
        if(customerId == null){
            return false;
        }
        cartRepository.deleteCartByCustomer(customerId);
        return true;
    }

    @Override
    public boolean increaseQuantity(Integer customerId, Integer dishId, Integer amount) {
        if(amount == null || amount <= 0){
            return false;
        }

        CartItem item = cartRepository.getCartItem(customerId, dishId);
        if(item == null){
            return false;
        }

        int newQuatity = item.getQuantity() + amount;
        return updateCartItemQuantity(customerId, dishId, newQuatity);
    }

    @Override
    public boolean decreaseQuantity(Integer customerId, Integer dishId, Integer amount) {
        if (amount == null || amount <= 0) {
            return false;
        }

        CartItem item = cartRepository.getCartItem(customerId, dishId);
        if (item == null) {
            return false;
        }

        int newQuantity = Math.max(0, item.getQuantity() - amount);
        return updateCartItemQuantity(customerId, dishId, newQuantity);
    }


    @Override
    public Cart applyDiscount(Integer customerId, double discountPercent) {
        Cart cart = getCart(customerId);
        double newTotalPrice = cart.getTotalPrice()*(1- discountPercent);
        cart.setTotalPrice(newTotalPrice);

        for (CartItem item : cart.getItems()){
            double newSubtotal = item.getSubtotal() * (1 - discountPercent);
            item.setSubtotal(newSubtotal);
        }
        return cart;
    }

    @Override
    public boolean isCartEmpty(Integer customerId) {
        Cart cart = getCart(customerId);
        return cart.getItems().isEmpty();
    }

    @Override
    public int getCartItemCount(Integer customerId) {
        Cart cart = getCart(customerId);
        return cart.getTotalQuantity();
    }
}
