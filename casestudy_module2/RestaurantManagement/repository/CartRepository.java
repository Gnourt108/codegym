package casestudy_module2.RestaurantManagement.repository;

import casestudy_module2.RestaurantManagement.entity.Cart;
import casestudy_module2.RestaurantManagement.entity.CartItem;
import casestudy_module2.RestaurantManagement.entity.Dish;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartRepository {

    private static final String CART_FILE = "data/carts.csv";
    private DishRepository dishRepository;

    public CartRepository(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
        FileExists(CART_FILE);
        FileExists(CART_FILE);
    }
    private void FileExists(String file) {
        File f = new File(file);
        File parent = f.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        if (!f.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
                writer.write("customerId,dishId,quantity");
                writer.newLine();
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }
    }

    public List<CartItem> getCartItemsByCustomer(Integer customerId){
        return loadCartItems(CART_FILE, customerId);
    }

    public List<CartItem> getAllCartItems(){
        return loadAllCartItems(CART_FILE);
    }


    public boolean existsInCart(Integer customerId, Integer dishId) {
        List<CartItem> items = getCartItemsByCustomer(customerId);
        for (CartItem item : items) {
            if (item.getDishId().equals(dishId)) {
                return true;
            }
        }
        return false;
    }

    public CartItem getCartItem(Integer customerId, Integer dishId) {
        List<CartItem> items = getCartItemsByCustomer(customerId);
        for (CartItem item : items) {
            if (item.getDishId().equals(dishId)) {
                return item;
            }
        }
        return null;
    }

    public void saveCartItem(CartItem cartItem) {
        List<CartItem> allItems = getAllCartItems();

        allItems.removeIf(item ->
                item.getCustomerId().equals(cartItem.getCustomerId()) &&
                        item.getDishId().equals(cartItem.getDishId())
        );

        allItems.add(cartItem);

        saveAllCartItems(CART_FILE, allItems);
    }

    public void deleteCartByCustomer(Integer customerId) {
        List<CartItem> allItems = getAllCartItems();
        allItems.removeIf(item -> item.getCustomerId().equals(customerId));
        saveAllCartItems(CART_FILE, allItems);
    }

    public void deleteCartItem(Integer customerId, Integer dishId) {
        List<CartItem> allItems = getAllCartItems();

        allItems.removeIf(item ->
                item.getCustomerId().equals(customerId) &&
                        item.getDishId().equals(dishId)
        );

        saveAllCartItems(CART_FILE, allItems);
    }

    private List<CartItem> loadCartItems(String file, Integer customerId) {
        List<CartItem> items = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()){
                    continue;
                }

                String[] data = line.split(",");
                if (data.length >= 3) {
                    Integer cId = Integer.parseInt(data[0]);

                    if (cId.equals(customerId)) {
                        Integer dishId = Integer.parseInt(data[1]);
                        Integer quantity = Integer.parseInt(data[2]);

                        Dish dish = dishRepository.getDishById(dishId);
                        if (dish != null) {
                            double price = dishRepository.calculateDiscountedPrice(dish);
                            double subtotal = price * quantity;

                            CartItem item = new CartItem(
                                    cId, dishId, dish.getNameDish(),
                                    price, quantity, subtotal
                            );
                            items.add(item);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading cart: " + e.getMessage());
        }
        return items;
    }

    private List<CartItem> loadAllCartItems(String file) {
        List<CartItem> items = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");
                if (data.length >= 3) {
                    Integer customerId = Integer.parseInt(data[0]);
                    Integer dishId = Integer.parseInt(data[1]);
                    Integer quantity = Integer.parseInt(data[2]);

                    Dish dish = dishRepository.getDishById(dishId);
                    if (dish != null) {
                        double price = dishRepository.calculateDiscountedPrice(dish);
                        double subtotal = price * quantity;

                        CartItem item = new CartItem(
                                customerId, dishId, dish.getNameDish(),
                                price, quantity, subtotal
                        );
                        items.add(item);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi đọc file cart: " + e.getMessage());
        }

        return items;
    }

    private void saveAllCartItems(String file, List<CartItem> items) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("customerId,dishId,quantity");
            writer.newLine();

            for (CartItem item : items) {
                writer.write(item.getCustomerId() + "," +
                        item.getDishId() + "," +
                        item.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Lỗi ghi file cart: " + e.getMessage());
        }
    }
}
