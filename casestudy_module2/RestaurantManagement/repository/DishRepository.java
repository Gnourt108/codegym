package casestudy_module2.RestaurantManagement.repository;

import casestudy_module2.RestaurantManagement.entity.Dish;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DishRepository {

    private static final String CSV_FILE = "data/dishes.csv";

    public DishRepository(){
        FileExists();
    }

    private void FileExists() {
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


    public void addDish(Dish dish){
        List<Dish> all = getAllDishes();

        int orderId = 0;
        for (Dish d : all){
            if(d.getIdDish() != null && d.getIdDish() > orderId){
                orderId = d.getIdDish();
            }
        }
        dish.setIdDish(orderId + 1);
        all.add(dish);
        saveAll(all);
    }


    public void deleteDish(String dishName){
        List<Dish> all = getAllDishes();
        all.removeIf(d -> d.getNameDish().toLowerCase().contains(dishName.toLowerCase()));
        saveAll(all);
    }

    public void updateDish(Dish dish){
        List<Dish> all = getAllDishes();
        for (int i = 0; i < all.size(); i++) {
            if(all.get(i).getIdDish().equals(dish.getIdDish())){
                all.set(i, dish);
                saveAll(all);
                return;
            }
        }
    }

    public List<Dish> getAllDishes(){
        List<Dish> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))){
            String line = reader.readLine();
            while((line = reader.readLine()) != null){
                if(line.trim().isEmpty()){
                    continue;
                }

                String[] data = parseCsvLine(line);
                Dish dish = parseFromCsv(data);
                if(dish != null){
                    result.add(dish);
                }
            }
        }catch (IOException e){
            System.out.println("Lỗi đọc file: "+e.getMessage());
        }
        return result;
    }

    public Dish getDishById(Integer dishId){
        List<Dish> all = getAllDishes();
        for (Dish d : all){
            if(d.getIdDish().equals(dishId)){
                return d;
            }
        }
        return null;
    }

    public List<Dish> searchDishesByName(String name){
        List<Dish> all = getAllDishes();
        List<Dish> resultList = new ArrayList<>();
        for (Dish d : all){
            if(d.getNameDish().equals(name)){
                resultList.add(d);
            }
        }
        return resultList;
    }
    public List<Dish> getDiscountedDishes(){
        List<Dish> all = getAllDishes();
        List<Dish> list = new ArrayList<>();
        long now = System.currentTimeMillis();
        for (Dish dish : all){
            if(dish.isEndow() && dish.getIsDiscount() > 0 && now <= dish.getDiscountDeadline()){
                list.add(dish);
            }
        }
        return list;
    }

    public List<Dish> getBestSellerDishes(){
        List<Dish> list = new ArrayList<>();
        List<Dish> all = getAllDishes();
        for (Dish d : all){
            if(d.isBestSeller()){
                list.add(d);
            }
        }
        return list;
    }

    public double calculateDiscountedPrice(Dish dish){
        long now = System.currentTimeMillis();

        if(!dish.isEndow() || dish.getIsDiscount() <= 0 || now > dish.getDiscountDeadline()){
            return dish.getPrice();
        }
        return dish.getPrice() * (1 - dish.getIsDiscount());
    }

    private void saveAll(List<Dish> all) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))){
            writer.write(getCsvHeader());
            writer.newLine();

            for (Dish d : all){
                String[] data = toCsvArray(d);
                writer.write(toCsvLine(data));
                writer.newLine();
            }
        }catch (IOException e){
            System.out.println("Lỗi ghi file: "+e.getMessage());
        }

    }

    public Dish parseFromCsv(String[] data){
        if(data.length < 8){
            return null;
        }
        return new Dish(
                Integer.parseInt(data[0]),
                data[1],
                Double.parseDouble(data[2]),
                Integer.parseInt(data[3]),
                Boolean.parseBoolean(data[4]),
                Double.parseDouble(data[5]),
                Boolean.parseBoolean(data[6]),
                Long.parseLong(data[7])
        );
    }

    public String[] toCsvArray(Dish dish){
        return new String[]{
                dish.getIdDish().toString(),
                dish.getNameDish(),
                String.valueOf(dish.getPrice()),
                String.valueOf(dish.getQuality()),
                String.valueOf(dish.isEndow()),
                String.valueOf(dish.getIsDiscount()),
                String.valueOf(dish.isBestSeller()),
                String.valueOf(dish.getDiscountDeadline())
        };
    }

    private String getCsvHeader() {
        return "idDish,nameDish,price,quality,isEndow,isDiscount,isBestSeller,discountDeadline";
    }

    private String[] parseCsvLine(String line){
        return line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    }

    private String toCsvLine(String[] data){
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            String field = data[i];

            if(field.contains(",") || field.contains("\"") || field.contains("\n")){
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
