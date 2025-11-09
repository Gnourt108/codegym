package ss12_map.ProductManagement.service;

import ss12_map.ProductManagement.entity.Product;
import ss12_map.ProductManagement.repository.ProductRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ProductService implements IProductService {

    private final ProductRepository repo = new ProductRepository();

    @Override
    public List<Product> getAll() {
        return repo.getAll();
    }

    @Override
    public boolean addProduct(Product product) {
        if(isExist(product.getProductId())){
            return false;
        }
        repo.addProduct(product);
        return true;
    }

    @Override
    public boolean updateProduct(Product product) {
        if(!isExist(product.getProductId())){
            return false;
        }
        repo.updateProduct(product);
        return true;
    }

    @Override
    public boolean deleteProduct(int id) {
        if(!isExist(id)){
            return false;
        }
        repo.deleteProduct(id);
        return true;
    }

    @Override
    public Product findProductById(int id) {
        return repo.findById(id);
    }

    @Override
    public List<Product> searchByName(String name) {
        return repo.searchProductByName(name);
    }

    @Override
    public boolean isExist(int id) {
        return repo.findById(id) != null;
    }

    @Override
    public List<Product> sortByPriceAsc() {
        return repo.getAll().stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> sortByPriceDesc() {
        return repo.getAll().stream()
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> sortByNameAsc() {
        return repo.getAll().stream()
                .sorted(Comparator.comparing(p -> p.getProductName().toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> sortByNameDesc() {
        return repo.getAll().stream()
                .sorted(Comparator.comparing(p -> p.getProductName().toLowerCase(), Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
}
