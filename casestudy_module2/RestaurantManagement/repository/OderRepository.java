package casestudy_module2.RestaurantManagement.repository;

import casestudy_module2.RestaurantManagement.entity.Dish;
import casestudy_module2.RestaurantManagement.entity.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OderRepository {

    private static final String CSV_FILE = "data/orders.csv";
    private DishRepository dishRepository;

    public OderRepository(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
        FileExists();
    }

    private void FileExists(){
        File file = new File(CSV_FILE);
        File parent = file.getParentFile();
        if(parent != null && !parent.exists()){
            parent.mkdirs();
        }

        if(!file.exists()){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))){
                writer.write(getCsvHeader());
                writer.newLine();
            }catch (IOException e){
                System.out.println("Lỗi tạo file: "+e.getMessage());
            }
        }
    }

    public void add(Order order){
        List<Order> all = getAll();
        int orderId = 0;
        for (Order o : all){
            if(o.getOrderID() != null && o.getOrderID() > orderId){
                orderId = o.getOrderID();
            }
        }

        order.setOrderID(orderId + 1);
        all.add(order);
        saveAll(all);
    }

    public void update(Order order){
        List<Order> all = getAll();
        for (int i = 0; i < all.size(); i++) {
            if(all.get(i).getOrderID().equals(order.getOrderID())){
                all.set(i, order);
                saveAll(all);
                return;
            }
        }
    }

    public Order getById(Integer id){
        List<Order> all = getAll();
        for (Order o : all){
            if(o.getOrderID().equals(id)){
                return o;
            }
        }
        return null;
    }

    public List<Order> getAll() {
        List<Order> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))){
            String line = reader.readLine(); // Đọc header

            while ((line = reader.readLine())!=null){
                if(line.trim().isEmpty()){
                    continue;
                }

                String[] data = parseCsvLine(line);

                // Bỏ qua dòng header nếu đọc lại
                if (data.length > 0 && data[0].equals("orderID")) {
                    continue;
                }

                Order order = parseFromCsv(data);
                if(order != null){
                    result.add(order);
                }
            }
        } catch (IOException e) {
            System.out.println("Không thể đọc file: "+e.getMessage());
        }
        return result;
    }

    public List<Order> getOrderByCustomer(Integer customerId){
        if(customerId == null) {
            return new ArrayList<>();
        }

        List<Order> allOrders = getAll();
        List<Order> customerOrders = new ArrayList<>();

        for(Order order : allOrders) {
            if(order.getCustomerId() != null && order.getCustomerId().equals(customerId)) {
                customerOrders.add(order);
            }
        }

        return customerOrders;
    }

    public List<Order> getOrdersByShipper(Integer shipperId) {
        return new ArrayList<>();
    }

    public List<Order> getPendingOrders(){
        List<Order> all = getAll();
        List<Order> result = new ArrayList<>();

        for (Order order : all){
            if("PENDING".equals(order.getStatus())){
                result.add(order);
            }
        }
        return result;
    }

    public void saveAll(List<Order> orders){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))){
            writer.write(getCsvHeader());
            writer.newLine();
            for (Order o : orders){
                String[] data = toCsvArray(o);
                writer.write(toCsvLine(data));
                writer.newLine();
            }
        }catch (IOException e){
            System.out.println("Lỗi ghi file: "+e.getMessage());
        }
    }

    public Order parseFromCsv(String[] data){
        if(data.length < 4){
            return null;
        }

        try {
            Order o = new Order();
            o.setOrderID(Integer.parseInt(data[0].trim()));

            List<Dish> orderDishes = new ArrayList<>();
            String dishIdsStr = data[1].trim();

            if(!dishIdsStr.isEmpty()){
                String[] dishIds = dishIdsStr.split(";");
                for (String id : dishIds){
                    String trimmedId = id.trim();
                    if(!trimmedId.isEmpty()){
                        try {
                            Dish dish = dishRepository.getDishById(Integer.parseInt(trimmedId));
                            if(dish != null){
                                orderDishes.add(dish);
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Lỗi parse dishId: " + trimmedId);
                        }
                    }
                }
            }
            o.setDishes(orderDishes);
            o.setStatus(data[2].trim());
            o.setPay(Boolean.parseBoolean(data[3].trim()));

            if(data.length >= 5 && !data[4].trim().isEmpty()) {
                o.setCustomerId(Integer.parseInt(data[4].trim()));
            }

            return o;
        } catch (NumberFormatException e) {
            System.err.println("Lỗi parse order: " + String.join(",", data));
            e.printStackTrace();
            return null;
        }
    }

    private String[] toCsvArray(Order o){
        StringBuilder dishIds = new StringBuilder();
        if(o.getDishes() != null){
            for (int i = 0; i < o.getDishes().size(); i++) {
                dishIds.append(o.getDishes().get(i).getIdDish());
                if(i < o.getDishes().size() - 1){
                    dishIds.append(";");
                }
            }
        }
        return new String[]{
                o.getOrderID().toString(),
                dishIds.toString(),
                o.getStatus(),
                String.valueOf(o.isPay()),
                o.getCustomerId() != null ? o.getCustomerId().toString() : ""
        };
    }

    private String getCsvHeader(){
        return "orderID,dishIds,status,isPay,customerId";
    }

    private String[] parseCsvLine(String line){
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(current.toString().trim());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }
        result.add(current.toString().trim());

        return result.toArray(new String[0]);
    }

    private String toCsvLine(String[] data){
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < data.length; i++) {
            String field = data[i];

            if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
                field = "\"" + field.replace("\"", "\"\"") + "\"";
            }

            line.append(field);
            if (i < data.length - 1) {
                line.append(",");
            }
        }
        return line.toString();
    }
}