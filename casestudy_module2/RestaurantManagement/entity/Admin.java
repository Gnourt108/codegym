package casestudy_module2.RestaurantManagement.entity;

public class Admin extends Account{

    public Admin(Integer idUser, String userName, String password, String phoneNumber, String address, boolean isLocked) {
        super(idUser, userName, password, phoneNumber, address, isLocked);
    }
}
