package ss12_map.ProductManagement.service;

import ss12_map.ProductManagement.entity.Product;

import java.util.List;

interface IProductService {
    List<Product> getAll();
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(int id);
    Product findProductById(int id);
    List<Product> searchByName(String name);
    boolean isExist(int id);
    List<Product> sortByPriceAsc();
    List<Product> sortByPriceDesc();
    List<Product> sortByNameAsc();
    List<Product> sortByNameDesc();
}
