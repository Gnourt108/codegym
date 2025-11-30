package casestudy_module2.RestaurantManagement.repository;

import casestudy_module2.RestaurantManagement.entity.Customer;
import casestudy_module2.RestaurantManagement.entity.Dish;
import casestudy_module2.RestaurantManagement.entity.Restaurant;
import casestudy_module2.RestaurantManagement.entity.Shipper;
import casestudy_module2.RestaurantManagement.service.RestaurantServices.IRestaurantService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RestaurantRepository extends AccountRepository<Restaurant> {

    public RestaurantRepository(String csvFile) {
        super("data/restaurants.csv");
    }

    @Override
    protected Restaurant parseFromCsv(String[] data) {
        if(data.length < 7){
            return null;
        }

        Restaurant restaurant = new Restaurant(
                Integer.parseInt(data[0]),
                data[1],
                data[2],
                data[3],
                data[4],
                Boolean.parseBoolean(data[5])
        );
        return restaurant;
    }

    @Override
    protected String[] toCsvArray(Restaurant r) {
        return new String[] {
            r.getIdUser().toString(),
            r.getUserName(),
            r.getPassword(),
            r.getPhoneNumber(),
            r.getAddress(),
            String.valueOf(r.isLocked()),
            r.getHostRestaurant() != null ? r.getHostRestaurant() : "",
        };
    }

    @Override
    protected String getCsvHeader() {
        return "idUser,userName,password,phoneNumber,address,isLocked,hostRestaurant";
    }
}
