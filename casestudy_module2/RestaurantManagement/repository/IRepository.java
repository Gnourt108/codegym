package casestudy_module2.RestaurantManagement.repository;

import java.util.List;

public interface IRepository <T>{
    void add (T entity);
    void update (T entity);
    void delete(Integer entity);
    T getById(Integer id);
    List<T> getAll();
}
