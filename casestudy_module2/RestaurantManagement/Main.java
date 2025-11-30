package casestudy_module2.RestaurantManagement;

import casestudy_module2.RestaurantManagement.repository.*;
import casestudy_module2.RestaurantManagement.service.AdminServices.AdminService;
import casestudy_module2.RestaurantManagement.service.CartServices.CartService;
import casestudy_module2.RestaurantManagement.service.CustomerServices.CustomerService;
import casestudy_module2.RestaurantManagement.service.DishServices.DishService;
import casestudy_module2.RestaurantManagement.service.OrderServices.OrderService;
import casestudy_module2.RestaurantManagement.service.RestaurantServices.RestaurantService;
import casestudy_module2.RestaurantManagement.service.ShipperServices.ShipperService;
import casestudy_module2.RestaurantManagement.view.MainView;

public class Main {
    public static void main(String[] args) {
        CustomerRepository customerRepository = new CustomerRepository();
        RestaurantRepository restaurantRepository = new RestaurantRepository("data/restaurants.csv");
        ShipperRepository shipperRepository = new ShipperRepository("data/shippers.csv");
        DishRepository dishRepository = new DishRepository();
        CartRepository cartRepository = new CartRepository(dishRepository);
        OderRepository orderRepository = new OderRepository(dishRepository);

        CustomerService customerService = new CustomerService(customerRepository);
        RestaurantService restaurantService = new RestaurantService(restaurantRepository);
        ShipperService shipperService = new ShipperService(shipperRepository);
        DishService dishService = new DishService(dishRepository);
        CartService cartService = new CartService(cartRepository, dishRepository);
        OrderService orderService = new OrderService(orderRepository, customerRepository,
                dishRepository, cartRepository, cartService);
        AdminService adminService = new AdminService(customerRepository, restaurantRepository,
                shipperRepository);

        MainView mainView = new MainView(customerService, restaurantService, shipperService,
                dishService, cartService, orderService, adminService);
        mainView.showMainMenu();
    }
}
