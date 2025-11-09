package ss12_map.ProductManagement.controller;

import common.InputHelper;
import ss12_map.ProductManagement.entity.Product;
import ss12_map.ProductManagement.service.ProductService;

import java.util.List;
import java.util.Scanner;

public class ProductController {
    private ProductService productService = new ProductService();
    private Scanner sc = new Scanner(System.in);

    public boolean addProduct(Product product){
        return productService.addProduct(product);
    }

    public boolean updateProduct(Product product){
        return productService.updateProduct(product);
    }

    public boolean deleteProduct(int id){
        return productService.deleteProduct(id);
    }

    public List<Product> getAll(){
        return productService.getAll();
    }

    public List<Product> searchProductByName(String name){
        return productService.searchByName(name);
    }

    public Product findProductById(int id){
        return productService.findProductById(id);
    }

    public List<Product> sortByPriceAsc(){
        return productService.sortByPriceAsc();
    }

    public List<Product> sortByPriceDesc(){
        return productService.sortByNameDesc();
    }

    public List<Product> sortByNameAsc(){
        return productService.sortByNameAsc();
    }

    public List<Product> sortByNameDesc(){
        return productService.sortByNameDesc();
    }
}
