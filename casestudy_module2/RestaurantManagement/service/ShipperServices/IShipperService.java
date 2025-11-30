package casestudy_module2.RestaurantManagement.service.ShipperServices;

import casestudy_module2.RestaurantManagement.entity.Shipper;
import casestudy_module2.RestaurantManagement.service.IAccountService;

import java.util.List;

public interface IShipperService {
    boolean register(Shipper shipper);
    boolean login(String username, String password);
    boolean changePassword(Integer shipperId, String oldPassword, String newPassword);
    boolean updateProfile(Shipper shipper);

    Shipper getById(Integer shipperId);
    Shipper getByUsername(String username);
    List<Shipper> getAllShippers();
    List<Shipper> searchByUsername(String username);

    List<Shipper> getAvailableShippers();
    boolean setShipperBusy(Integer shipperId, boolean busy);
    boolean isShipperAvailable(Integer shipperId);
    boolean isAccountLocked(Integer shipperId);

    Shipper assignShipper();
}
