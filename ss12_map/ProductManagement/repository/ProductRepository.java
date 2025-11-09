package ss12_map.ProductManagement.repository;

import ss12_map.ProductManagement.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static List<Product> products = new ArrayList<>();
    private static int nextId = 1;

    static {
        products.add(new Product(nextId++, "Album Rosie", 23000));
        products.add(new Product(nextId++, "Album Ruby", 23000));
        products.add(new Product(nextId++, "Album eternal sunshine", 22200));
    }

    public List<Product> getAll(){
        return products;
    }

    public void addProduct(Product product){
        if (product.getProductId() <= 0) {
            product.setProductId(nextId++);
        }
        products.add(product);
    }

    public void updateProduct(Product updatedProduct){
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getProductId() == updatedProduct.getProductId()){
                products.set(i, updatedProduct);
                return;
            }
        }
    }

    public void deleteProduct(int id){
        products.removeIf(p -> p.getProductId() == id);
    }

    public List<Product> searchProductByName(String name){
        List<Product> listResult = new ArrayList<>();
        for (Product p : products){
            if(p.getProductName().toLowerCase().contains(name.toLowerCase())){
                listResult.add(p);
            }
        }
        return listResult;
    }

    public Product findById(int id){
        for (Product p : products){
            if(p.getProductId() == id){
                return p;
            }
        }
        return null;
    }
}
