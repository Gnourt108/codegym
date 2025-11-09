package ss12_map.ProductManagement.view;

import common.InputHelper;
import ss10_DSA_StudentCRUD.entity.Student;
import ss12_map.ProductManagement.controller.ProductController;
import ss12_map.ProductManagement.entity.Product;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProductView {
    private final ProductController controller = new ProductController();
    public void view(){
        int choice;
        do{
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║       QUẢN LÝ SẢN PHẨM (MVC)       ║");
            System.out.println("╠════════════════════════════════════╣");
            System.out.println("║ 1. Thêm sản phẩm                   ║");
            System.out.println("║ 2. Sửa sản phẩm theo ID            ║");
            System.out.println("║ 3. Xóa sản phẩm theo ID            ║");
            System.out.println("║ 4. Hiển thị danh sách sản phẩm     ║");
            System.out.println("║ 5. Tìm kiếm theo tên               ║");
            System.out.println("║ 6. Sắp xếp sản phẩm                ║");
            System.out.println("║ 0. Thoát chương trình              ║");
            System.out.println("╚════════════════════════════════════╝");

            choice = InputHelper.inputInt("Nhập lựa chọn của bạn (0-6): ");
            switch (choice){
                case 1 :
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    displayAllProducts();
                    break;
                case 5:
                    searchProductByName();
                    break;
                case 6:
                    sortProduct();
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình");
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng lựa chọn lại!");
            }
        }while(choice != 0);
    }

    private void sortProduct() {
        System.out.println("Sắp xếp sản phẩm");
        System.out.println("1. Theo giá tăng dần");
        System.out.println("2. Theo giá giảm dần");
        System.out.println("3. Theo tên A → Z");
        System.out.println("4. Theo tên Z → A");
        int opt = InputHelper.inputInt("Chọn cách sắp xếp: ");
        List<Product> sortedList = null;
        switch (opt){
            case 1:
                sortedList = controller.sortByPriceAsc();
                break;
            case 2:
                sortedList = controller.sortByPriceDesc();
                break;
            case 3:
                sortedList = controller.sortByNameAsc();
                break;
            case 4:
                sortedList = controller.sortByNameDesc();
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
        }
        if(sortedList != null){
            showProducts(sortedList);
        }
    }

    private void searchProductByName() {
        System.out.println("Tìm kiếm sản phẩm theo tên: ");
        String name = InputHelper.inputString("Nhập tên sản phẩm muốn tìm kiếm: ");
        List<Product> listResult = controller.searchProductByName(name);

        if(listResult.isEmpty()){
            System.out.println("Không tìm thấy sản phẩm nào có tên là: "+name);
        }else{
            showProducts(listResult);
        }
    }

    private void displayAllProducts() {
        System.out.println("Danh sách sản phẩm");
        List<Product> products = controller.getAll();
        showProducts(products);
    }

    private void deleteProduct() {
        System.out.println("Xóa sản phẩm");
        int id = InputHelper.inputInt("Nhập mã sản phẩm muốn xóa: ");
        if(controller.findProductById(id) == null){
            System.out.println("Không tìm thấy sản phẩm với mã là: "+id);
            return;
        }

        if(controller.deleteProduct(id)){
            System.out.println("Xóa sản phẩm thành công");
        }else{
            System.out.println("Xóa sản phẩm thất bại");
        }

    }

    private void updateProduct() {
        System.out.println("Cập nhật sản phẩm");
        int id = InputHelper.inputInt("Nhập mã sản phẩm muốn chỉnh sửa: ");
        if(controller.findProductById(id) == null){
            System.out.println("Không tìm thấy sản phẩm nào với mã là : "+id);
            return;
        }

        String name = InputHelper.inputString("Nhập tên sản phẩm mới: ");
        double price = InputHelper.inputDouble("Nhập giá sản phẩm mới: ");
        Product updatedProduct = new Product(id, name, price);

        if(controller.updateProduct(updatedProduct)){
            System.out.println("Cập nhật sản phẩm thành công");
        }else{
            System.out.println("Cập nhật sản phẩm thất bại");
        }
    }

    private void addProduct() {
        System.out.println("Thêm sản phẩm mới");
        String name = InputHelper.inputString("Nhập tên sản phẩm");
        double price = InputHelper.inputDouble("Nhập giá sản phẩm: ");

        Product product = new Product(0, name, price);

        if(controller.addProduct(product)){
            System.out.println("Thêm sản phẩm thành công");
        }else{
            System.out.println("Thêm sản phẩm thất bại");
        }
    }

    public static void showProducts(List<Product> products){
        if (products.isEmpty()) {
            System.out.println("Danh sách sinh viên đang trống");
            return;
        }
        System.out.println("\n===== DANH SÁCH SẢN PHẨM =====");
        System.out.printf("%-5s | %-25s | %-5s%n", "ID", "Tên sản phẩm", "Giá");
        System.out.println("---------------------------------------------------------------");

        for (Product p : products) {
            System.out.printf("%-5d | %-25s | %-5.2f%n",
                    p.getProductId(),
                    p.getProductName(),
                    p.getPrice());
        }
        System.out.println("---------------------------------------------------------------");
    }
}
