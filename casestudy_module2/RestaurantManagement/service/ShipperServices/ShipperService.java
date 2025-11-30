package casestudy_module2.RestaurantManagement.service.ShipperServices;

import casestudy_module2.RestaurantManagement.entity.Shipper;
import casestudy_module2.RestaurantManagement.repository.ShipperRepository;

import java.util.List;

public class ShipperService implements IShipperService{
    private final ShipperRepository repository;

    public ShipperService(ShipperRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean register(Shipper shipper) {
        if (shipper == null || shipper.getUserName() == null || shipper.getPassword() == null) {
            return false;
        }

        if (repository.getByUsername(shipper.getUserName()) != null) {
            return false;
        }

        repository.add(shipper);
        return true;
    }

    @Override
    public boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }

        Shipper shipper = repository.getByUsername(username);

        if (shipper == null) {
            return false;
        }

        if (shipper.isLocked()) {
            System.out.println("Tài khoản đã bị khóa!");
            return false;
        }

        return shipper.getPassword().equals(password);
    }

    @Override
    public boolean changePassword(Integer shipperId, String oldPassword, String newPassword) {
        if (shipperId == null || oldPassword == null || newPassword == null) {
            return false;
        }

        Shipper shipper = repository.getById(shipperId);
        if (shipper == null) {
            return false;
        }

        if (!shipper.getPassword().equals(oldPassword)) {
            return false;
        }

        if (newPassword.length() < 6) {
            return false;
        }

        shipper.setPassword(newPassword);
        repository.update(shipper);
        return true;
    }

    @Override
    public boolean updateProfile(Shipper shipper) {
        if (shipper == null || shipper.getIdUser() == null) {
            return false;
        }

        if (repository.getById(shipper.getIdUser()) == null) {
            return false;
        }

        repository.update(shipper);
        return true;
    }

    @Override
    public Shipper getById(Integer shipperId) {
        if (shipperId == null) {
            return null;
        }
        return repository.getById(shipperId);
    }

    @Override
    public Shipper getByUsername(String username) {
        if (username == null) {
            return null;
        }
        return repository.getByUsername(username);
    }

    @Override
    public List<Shipper> getAllShippers() {
        return repository.getAll();
    }

    @Override
    public List<Shipper> searchByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return getAllShippers();
        }
        return repository.searchByUsername(username);
    }

    @Override
    public List<Shipper> getAvailableShippers() {
        return repository.getAvailableShippers();
    }

    @Override
    public boolean setShipperBusy(Integer shipperId, boolean busy) {
        if(shipperId == null){
            return false;
        }

        Shipper shipper = repository.getById(shipperId);
        if(shipper == null){
            return false;
        }

        repository.setShipperBusy(shipperId, busy);
        return true;
    }

    @Override
    public boolean isShipperAvailable(Integer shipperId) {
        Shipper shipper = repository.getById(shipperId);
        return shipper != null && !shipper.isBusy() && !shipper.isLocked();
    }

    @Override
    public boolean isAccountLocked(Integer shipperId) {
        Shipper shipper = repository.getById(shipperId);
        return shipper != null && shipper.isLocked();
    }

    @Override
    public Shipper assignShipper() {
        List<Shipper> availableShippers = getAvailableShippers();
        if(availableShippers.isEmpty()){
            return null;
        }

        Shipper assignedShipper = availableShippers.get(0);
        setShipperBusy(assignedShipper.getIdUser(), true);
        return assignedShipper;
    }
}
