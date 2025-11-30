package casestudy_module2.RestaurantManagement.service;

import java.util.List;

public interface IAccountService <T>{

    boolean addAccount(T account);

    boolean updateAccount(T account);

    boolean deleteAccount(Integer idUser);

    T getById(Integer idUser);

    List<T> getAll();

    List<T> searchByUsername(String username);

    void lockAccount(Integer idUser);

    void unlockAccount(Integer idUser);

    boolean login(String username, String password);

    boolean changePassword(Integer idUser, String newPassword);
}
